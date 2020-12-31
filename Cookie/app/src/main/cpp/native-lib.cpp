//
// Created by USER on 12/30/2020.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jint JNICALL
Java_id_ac_ui_cs_mobileprogramming_prissy_cookie_external_PortionCategory_categorizePortion(JNIEnv *env, jobject thiz, jint portion) {
    if (portion >= 5) {
        return 2;
    } else if (portion > 1 && portion < 5) {
        return 1;
    }
    return 0;
}
