/*
 * Copyright 2020 Google LLC
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
package com.google.android.ground.persistence.local

import android.content.Context
import androidx.room.Room
import com.google.android.ground.Config
import com.google.android.ground.coroutines.IoDispatcher
import com.google.android.ground.persistence.local.room.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asExecutor

@InstallIn(SingletonComponent::class)
@Module
object LocalDatabaseModule {
  @Provides
  @Singleton
  fun localDatabase(
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
  ): LocalDatabase {
    return Room.databaseBuilder(context, LocalDatabase::class.java, Config.DB_NAME)
      .fallbackToDestructiveMigration() // TODO(#128): Disable before official release.
      // Run queries and transactions on background I/O thread.
      .setQueryExecutor(ioDispatcher.asExecutor())
      .build()
  }
}
