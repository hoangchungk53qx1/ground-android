/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gnd.ui.terms;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gnd.model.TermsOfService;
import com.google.android.gnd.repository.TermsOfServiceRepository;
import com.google.android.gnd.rx.Loadable;
import com.google.android.gnd.rx.annotations.Hot;
import com.google.android.gnd.ui.common.AbstractViewModel;
import com.google.android.gnd.ui.common.Navigator;
import javax.inject.Inject;
import timber.log.Timber;

// TODO: Needs to handle view state and behaviors of the Terms Fragment
public class TermsOfServiceViewModel extends AbstractViewModel {

  private final Navigator navigator;

  @Hot(replays = true)
  public final MutableLiveData<String> termsOfServiceText = new MutableLiveData<>();

  @Hot(replays = true)
  public final MutableLiveData<Boolean> acceptTermsCheckboxState = new MutableLiveData<>();

  @Hot(replays = true)
  public final MutableLiveData<Boolean> termsOfServiceLoadState = new MutableLiveData<>(false);

  private final TermsOfServiceRepository termsOfServiceRepository;
  private final LiveData<Loadable<TermsOfService>> termsOfService;

  @Inject
  public TermsOfServiceViewModel(
      Navigator navigator, TermsOfServiceRepository termsOfServiceRepository) {
    this.navigator = navigator;
    this.termsOfServiceRepository = termsOfServiceRepository;
    this.termsOfService =
        LiveDataReactiveStreams.fromPublisher(
            termsOfServiceRepository
                .getTermsOfService()
                .doOnSubscribe(__ -> Timber.d("Loading terms of service from remote"))
                .doOnError(err -> Timber.d("Failed to load terms of service from remote"))
                .toFlowable()
                .compose(Loadable::loadingOnceAndWrap)
                .defaultIfEmpty(Loadable.notFound()));
  }

  public void onButtonClicked() {
    termsOfServiceRepository.setTermsOfServiceAccepted(true);
    navigator.navigate(TermsOfServiceFragmentDirections.proceedToHomeScreen());
  }

  public LiveData<Loadable<TermsOfService>> getTermsOfService() {
    return termsOfService;
  }

  public void setTermsOfServiceTextView(String terms) {
    termsOfServiceText.setValue(terms);
  }

  public void setTermsOfServiceLoadState(boolean value) {
    termsOfServiceLoadState.setValue(value);
  }
}
