

#include <jni.h>
#include <unistd.h>
#include <sys/wait.h>

extern int patchMethod(int argc, char *argv[]);

extern int bsdiff_main(int argc, char *argv[]);

int main_exec(int (*mainFunc)(int, char **), int argc, char **argv) {
    int ret = fork();
    if (0 == ret) {
        _exit(mainFunc(argc, argv));
    } else if (-1 != ret) {
        wait(&ret);
    }
    return ret;
}

JNIEXPORT jboolean JNICALL
Java_com_hubertyoung_update_AppIncrementalUpdateUtil_bspatch
        (JNIEnv *env, jclass cls,
         jstring old, jstring new, jstring patch) {
    int argc = 4;
    char *argv[argc];
    argv[0] = "bspatch";
    argv[1] = (char *) ((*env)->GetStringUTFChars(env, old, 0));
    argv[2] = (char *) ((*env)->GetStringUTFChars(env, new, 0));
    argv[3] = (char *) ((*env)->GetStringUTFChars(env, patch, 0));

    int ret = main_exec(patchMethod, 4, argv);

    (*env)->ReleaseStringUTFChars(env, old, argv[1]);
    (*env)->ReleaseStringUTFChars(env, new, argv[2]);
    (*env)->ReleaseStringUTFChars(env, patch, argv[3]);
    return !ret;
}

JNIEXPORT jboolean JNICALL
Java_com_hubertyoung_update_AppIncrementalUpdateUtil_diff
        (JNIEnv *env, jclass type, jstring oldFilePath_, jstring newFilePath_,
         jstring patchPath_) {
    char *ch[5] = {0};
    ch[0] = "bsdiff";
    ch[1] = (*env)->GetStringUTFChars(env, oldFilePath_, 0);
    ch[2] = (*env)->GetStringUTFChars(env, newFilePath_, 0);
    ch[3] = (*env)->GetStringUTFChars(env, patchPath_, 0);

    int ret = main_exec(bsdiff_main, 4, ch);

    (*env)->ReleaseStringUTFChars(env, oldFilePath_, ch[1]);
    (*env)->ReleaseStringUTFChars(env, newFilePath_, ch[2]);
    (*env)->ReleaseStringUTFChars(env, patchPath_, ch[3]);

    return !ret;
}