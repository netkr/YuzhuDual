# 语竹双屏输入法 · YuzhuDual

<p align="center">
  <img src="docs/banner.png" alt="RP DUO 双屏输入法" width="360">
</p>

语竹双屏输入法是一款面向双屏 Android 掌机的中文输入法，基于 YuyanIme 与 Rime 构建，支持主屏与副屏键盘切换，并提供多种中文输入方式。

本仓库同时包含**完整源码**与**编译好的 APK**。

## 下载

- [下载最新 APK](https://github.com/netkr/YuzhuDual/releases/latest)
- [查看全部 Releases](https://github.com/netkr/YuzhuDual/releases)

> APK 体积约 129 MB，超过 GitHub 单文件 100 MB 上限，因此仅通过 Release 附件分发，不放入仓库文件树。

## 更新日志

**v20261002**
- 修复：跟随系统明暗模式时，亮色/暗色主题切换不生效
- 修复：收起按钮后副屏窗口不立即消失
- 修复：设置页对话框确定/取消按钮颜色与背景过于接近
- 优化：副屏 Presentation 复用，唤起速度提升
- 优化：石墨灰统一设置界面与深色主题

## 主要功能

- 中文全拼、简拼、双拼、笔画、手写和英文输入
- 主屏与副屏键盘显示切换，副屏窗口复用快速唤起
- 适配 Retroid Pocket Duo Lite 等双屏 Android 掌机
- GraphiteDark、GitHubDark 等深色主题
- 键盘高度、按键样式、候选词和双拼提示设置
- 石墨灰统一设置界面

## 源码构建

### 环境要求

- Android SDK（compileSdk 35）
- JDK 17
- 默认构建变体为 `dualscreen`，包名 `com.yuyan.pinyin.online`

### 构建 debug 版（无需签名配置）

```bash
export JAVA_HOME="/path/to/jdk17"
./gradlew assembleDualscreenDebug --no-daemon
```

APK 输出：`app/build/outputs/apk/dualscreen/debug/`

### 构建 release 版

release 构建需要自备签名文件。将 keystore 放入 `keystore/`，并创建 `keystore/keystore.properties`：

```properties
storeFile=your-release.jks
storePassword=your-password
keyAlias=your-alias
keyPassword=your-password
```

```bash
./gradlew assembleDualscreenRelease --no-daemon
```

签名文件与密码不会包含在本仓库中。

### 词库说明

Rime 词库位于 `app/src/main/assets/rime/build/`，其中 `*.bin` 为预编译词库文件。修改 `assets/rime` 内容后，需要递增 `CustomConstant.kt` 中的词库版本号，或清除应用数据后重新部署。

## 目录结构

```
app/src/main/java/com/yuyan/imemodule/
├── service/          # ImeService、副屏 Presentation
├── keyboard/         # 键盘容器与视图
├── data/theme/       # 主题定义与偏好
└── view/preference/  # 设置页组件
app/src/main/assets/rime/build/   # Rime 词库与方案
app/src/main/jniLibs/             # native 库
docs/banner.png                   # 项目宣传图
```

## 安装

1. 下载 APK。
2. 在 Android 设置中允许安装来自此来源的应用。
3. 安装后，在系统的"键盘/输入法"设置中启用"语竹双屏输入法"，并将其设为当前输入法。

## 隐私

输入和词库均在设备本地处理。本输入法不提供在线同步功能；剪贴板历史和自定义短语仅在用户启用相关功能后保存在本机。

## 许可证与致谢

本项目遵循 GPL-3.0 许可证，相关输入法和词库能力参考了 [YuyanIme](https://github.com/gurecn/YuyanIme) 和 [Rime](https://github.com/rime/librime)。
