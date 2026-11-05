package com.yuyan.imemodule.ui.fragment

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import com.yuyan.imemodule.R
import com.yuyan.imemodule.prefs.AppPrefs

class ImeSettingsFragment : PreferenceFragmentCompat() {

    private lateinit var screen: PreferenceScreen

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        screen = preferenceManager.createPreferenceScreen(requireContext())
        preferenceScreen = screen

        // 强制将双屏强制全屏开关置为开启
        AppPrefs.getInstance().dualScreen.dualForceFullscreenPrimary.setValue(true)

        addCategory(R.string.input_methods) {
            addDestinationPreference(R.string.setting_ime_input, R.string.setting_ime_input_summary, R.drawable.ic_menu_language, R.id.action_settingsFragment_to_inputSettingsFragment)
            addDestinationPreference(R.string.ime_settings_handwriting, R.string.ime_settings_handwriting_summary, R.drawable.ic_menu_handwriting, R.id.action_settingsFragment_to_handwritingSettingsFragment)
        }
        addCategory(R.string.keyboard) {
            addDestinationPreference(R.string.theme, R.string.theme_summary, R.drawable.ic_menu_theme, R.id.action_settingsFragment_to_themeSettingsFragment)
            addDestinationPreference(R.string.keyboard_feedback, R.string.keyboard_feedback_summary, R.drawable.ic_menu_touch, R.id.action_settingsFragment_to_keyboardFeedbackFragment)
            addDestinationPreference(R.string.setting_ime_keyboard, R.string.setting_ime_keyboard_summary, R.drawable.ic_menu_keyboard, R.id.action_settingsFragment_to_keyboardSettingFragment)
            addDestinationPreference(R.string.clipboard, R.string.clipboard_summary, R.drawable.ic_menu_clipboard, R.id.action_settingsFragment_to_clipboardSettingsFragment)
            // 已隐藏“全面屏键盘优化”入口
            // addDestinationPreference(R.string.full_display_keyboard, R.drawable.ic_menu_keyboard_full, R.id.action_settingsFragment_to_fullDisplayKeyboardFragment)
        }
        // 已隐藏“双屏”分组入口
        // addCategory(R.string.setting_dual_screen) {
        //     addDestinationPreference(R.string.setting_dual_screen, R.drawable.ic_menu_dualscreen, R.id.dualScreenSettingsFragment)
        // }
        addCategory(R.string.advanced) {
            addDestinationPreference(R.string.setting_ime_other, R.string.setting_ime_other_summary, R.drawable.ic_menu_more_horiz, R.id.action_settingsFragment_to_otherSettingsFragment)
            addPreference(R.string.about) {
                setIcon(R.drawable.ic_menu_feedback)
                setSummary(R.string.about_summary)
                setOnPreferenceClickListener {
                    findNavController().navigate(R.id.action_settingsFragment_to_aboutFragment)
                    true
                }
            }
        }
    }

    private fun addCategory(title: Int, category: PreferenceCategory.() -> Unit) {
        val cat = PreferenceCategory(requireContext()).apply {
            setTitle(title)
            isIconSpaceReserved = false
        }
        screen.addPreference(cat)
        category(cat)
    }

    private fun PreferenceCategory.addDestinationPreference(title: Int, summary: Int, icon: Int, action: Int) {
        val pref = Preference(requireContext()).apply {
            this.icon = createBadgedIcon(icon)
            setTitle(title)
            setSummary(summary)
            setOnPreferenceClickListener {
                findNavController().navigate(action)
                true
            }
        }
        addPreference(pref)
    }

    private fun PreferenceCategory.addPreference(title: Int, preference: Preference.() -> Unit) {
        val pref = Preference(requireContext()).apply {
            setTitle(title)
            preference()
            val ic = this.icon
            if (ic != null) {
                this.icon = createBadgedIcon(ic)
            }
        }
        addPreference(pref)
    }

    /**
     * 将图标放入圆角浅色容器：石墨灰系（日夜自适应），保持克制简约
     */
    private fun createBadgedIcon(iconRes: Int): Drawable {
        val ctx = requireContext()
        val icon = DrawableCompat.wrap(ctx.getDrawable(iconRes)!!.mutate())
        DrawableCompat.setTintList(icon, ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.graphite_text_primary)))
        val bg = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = dp(10f).toFloat()
            setColor(ContextCompat.getColor(ctx, R.color.graphite_surface_variant))
        }
        return object : LayerDrawable(arrayOf(bg, icon)) {
            override fun getIntrinsicWidth(): Int = dp(38f)
            override fun getIntrinsicHeight(): Int = dp(38f)
        }.apply {
            setLayerSize(0, dp(38f), dp(38f))
            setLayerGravity(0, Gravity.CENTER)
            setLayerSize(1, dp(22f), dp(22f))
            setLayerGravity(1, Gravity.CENTER)
        }
    }

    private fun createBadgedIcon(icon: Drawable): Drawable {
        val ctx = requireContext()
        val iconWrapped = DrawableCompat.wrap(icon.mutate())
        DrawableCompat.setTintList(iconWrapped, ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.graphite_text_primary)))
        val bg = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = dp(10f).toFloat()
            setColor(ContextCompat.getColor(ctx, R.color.graphite_surface_variant))
        }
        return object : LayerDrawable(arrayOf(bg, iconWrapped)) {
            override fun getIntrinsicWidth(): Int = dp(38f)
            override fun getIntrinsicHeight(): Int = dp(38f)
        }.apply {
            setLayerSize(0, dp(38f), dp(38f))
            setLayerGravity(0, Gravity.CENTER)
            setLayerSize(1, dp(22f), dp(22f))
            setLayerGravity(1, Gravity.CENTER)
        }
    }

    private fun dp(value: Float): Int = (value * resources.displayMetrics.density).toInt()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
