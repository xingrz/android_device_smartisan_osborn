/*
 * Copyright (c) 2018 The MoKee Open Source Project
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

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

abstract class PocketSensor implements SensorEventListener {

    private static final String TAG = "PocketSensor";
    private static final boolean DEBUG = false;

    protected final PocketModeService mService;

    protected final SensorManager mSensorManager;
    protected Sensor mSensor = null;

    PocketSensor(PocketModeService service) {
        mService = service;
        mSensorManager = (SensorManager) service.getSystemService(
                Context.SENSOR_SERVICE);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        /* Empty */
    }

    final void enable() {
        if (mSensor == null) return;
        if (DEBUG) Log.d(TAG, "Enabling");
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    final void disable() {
        if (mSensor == null) return;
        if (DEBUG) Log.d(TAG, "Disabling");
        mSensorManager.unregisterListener(this, mSensor);
    }

}
