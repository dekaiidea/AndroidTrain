#include <jni.h>
#include <opencv2/opencv.hpp>

extern "C" {

using namespace cv;
using namespace std;

JNIEXPORT jintArray JNICALL Java_net_raind_opencv3jni_MainActivity_grayProc(JNIEnv *env, jclass obj, jintArray buf, jint w, jint h){
    jboolean ptfalse = false;
    jint* srcBuf = env->GetIntArrayElements(buf, &ptfalse);
    if(srcBuf == NULL){
        return 0;
    }
    int size=w * h;

    Mat srcImage(h, w, CV_8UC4, (unsigned char*)srcBuf);
    Mat grayImage;
    cvtColor(srcImage, grayImage, COLOR_BGRA2GRAY);

    threshold(grayImage, grayImage, 128, 255, 0);

    cvtColor(grayImage, srcImage, COLOR_GRAY2BGRA);

    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, srcBuf);
    env->ReleaseIntArrayElements(buf, srcBuf, 0);
    return result;
}

}

