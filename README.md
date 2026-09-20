# 语竹双屏输入法 · YuzhuDual

语竹双屏输入法是面向双屏 Android 掌机的中文输入法适配项目，英文项目名为 **YuzhuDual**。

项目基于 [YuyanIme](https://github.com/gurecn/YuyanIme) 的输入法与 Rime 数据能力，针对双屏设备进行了适配和改进。

## 主要特性

- 支持中文全拼、简拼、双拼、笔画、手写和英文输入
- 支持主屏与副屏之间的键盘显示切换
- 针对 Retroid Pocket Duo Lite 的双屏布局适配
- 支持深色主题，包括 GraphiteDark 和 GitHubDark
- 支持离线语音输入：长按空格键即可说话输入
- 支持键盘高度、按键样式、候选词和双拼提示等设置

## 构建

环境要求：Android Studio、Android SDK、JDK 以及可用的 Gradle 环境。

```bash
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
./gradlew assembleDualscreenDebug --no-daemon
```

生成的 APK 位于：

```text
app/build/outputs/apk/dualscreen/debug/
```

## 下载 APK

APK 由本地构建后手动发布到 [Releases](https://github.com/netkr/YuzhuDual/releases)。在线仓库仅保留项目介绍和 APK 下载文件。

可直接下载[最新 APK](https://github.com/netkr/YuzhuDual/releases/latest)。

## 设备支持

当前主要针对 Retroid Pocket Duo Lite（Android 15，双屏）进行适配。其他双屏 Android 设备可能需要根据屏幕尺寸、density 和副屏行为进行调整。

## 项目参考与鸣谢

- [YuyanIme](https://github.com/gurecn/YuyanIme)
- [Rime](https://github.com/rime/librime)
- [sherpa-ncnn](https://github.com/k2-fsa/sherpa-ncnn)

## 许可证

本项目遵循 [GPL-3.0](LICENSE) 许可证。
