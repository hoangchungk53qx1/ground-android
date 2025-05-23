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
package org.groundplatform.android.ui.datacollection.tasks.time

import java.util.Date
import javax.inject.Inject
import org.groundplatform.android.model.submission.DateTimeTaskData.Companion.fromMillis
import org.groundplatform.android.ui.datacollection.tasks.AbstractTaskViewModel

class TimeTaskViewModel @Inject constructor() : AbstractTaskViewModel() {

  fun updateResponse(date: Date) {
    setValue(fromMillis(date.time))
  }
}
