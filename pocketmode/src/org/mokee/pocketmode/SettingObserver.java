/*
 * Copyright (c) 2017 The MoKee Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mokee.pocketmode;

import android.database.ContentObserver;
import android.util.Log;

import mokee.providers.MKSettings;

class SettingObserver extends ContentObserver {

    private static final String TAG = "PocketModeSetting";
    private static final boolean DEBUG = false;

    private PocketModeService mService;

    SettingObserver(PocketModeService service) {
        super(null);
        mService = service;
    }

    @Override
    public void onChange(boolean selfChange) {
        mService.onSettingChange();
    }

    void enable() {
        if (DEBUG) Log.d(TAG, "Enabling");
        mService.getContentResolver().registerContentObserver(
            MKSettings.System.getUriFor(MKSettings.System.PROXIMITY_ON_WAKE),
            false, this);
    }

    void disable() {
        if (DEBUG) Log.d(TAG, "Disabling");
        mService.getContentResolver().unregisterContentObserver(this);
    }

}
