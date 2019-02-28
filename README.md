# TechAssessment
Interview Task

## Build a debug APK ##
    type "gradlew assembleDebug" in command line

## Build and Install Apk ##
    connect device via usb with usb debugging enabled
    type "gradlew installDebug" in command line

## Instrumentation unit test ##
    type "gradlew connectedAndroidTest mergeAndroidReports --continue" in command line
    
## coverage report ##
        type "gradlew createDebugAndroidTestCoverageReport" in command line to create and view coverage reports