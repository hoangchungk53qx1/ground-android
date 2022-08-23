/*
 * Copyright 2018 Google LLC
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
package com.google.android.ground.model.submission

import com.google.android.ground.model.Survey
import com.google.android.ground.model.locationofinterest.LocationOfInterest
import com.google.android.ground.model.AuditInfo
import com.google.android.ground.model.job.Job
import com.google.android.ground.model.submission.ResponseMap
import com.google.android.ground.model.submission.Submission
import com.google.auto.value.AutoValue

/**
 * Represents a single instance of data collected about a specific [LocationOfInterest].
 *
 * @property created the user and time audit info pertaining to the creation of this submission.
 * @property lastModified the user and time audit info pertaining to the last modification of this
 * submission.
 */
data class Submission @JvmOverloads constructor(
    val id: String,
    val survey: Survey,
    val locationOfInterest: LocationOfInterest,
    val job: Job,
    val created: AuditInfo,
    val lastModified: AuditInfo,
    val responses: ResponseMap = ResponseMap.builder().build()
)