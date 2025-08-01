/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StringUtils
 *  org.lwjgl.input.Mouse
 */
package net.ccbluex.liquidbounce.ui.client.gui.clickgui.style.styles;

import java.awt.Color;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import net.ccbluex.liquidbounce.features.value.BlockValue;
import net.ccbluex.liquidbounce.features.value.BoolValue;
import net.ccbluex.liquidbounce.features.value.FloatValue;
import net.ccbluex.liquidbounce.features.value.FontValue;
import net.ccbluex.liquidbounce.features.value.IntegerValue;
import net.ccbluex.liquidbounce.features.value.ListValue;
import net.ccbluex.liquidbounce.features.value.Value;
import net.ccbluex.liquidbounce.font.CFontRenderer;
import net.ccbluex.liquidbounce.font.FontLoaders;
import net.ccbluex.liquidbounce.ui.client.gui.clickgui.Panel;
import net.ccbluex.liquidbounce.ui.client.gui.clickgui.elements.ButtonElement;
import net.ccbluex.liquidbounce.ui.client.gui.clickgui.elements.ModuleElement;
import net.ccbluex.liquidbounce.ui.client.gui.clickgui.style.Style;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.ui.font.GameFontRenderer;
import net.ccbluex.liquidbounce.ui.i18n.LanguageManager;
import net.ccbluex.liquidbounce.utils.block.BlockUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.lwjgl.input.Mouse;

public class JelloStyle
extends Style {
    private boolean mouseDown;
    private boolean rightMouseDown;

    public static float drawSlider(float value, float min, float max, int x, int y, int width, int mouseX, int mouseY, Color color) {
        float displayValue = Math.max(min, Math.min(value, max));
        float sliderValue = (float)x + (float)width * (displayValue - min) / (max - min);
        RenderUtils.drawRect((float)x, (float)y, (float)(x + width), (float)(y + 2), Integer.MAX_VALUE);
        RenderUtils.drawRect((float)x, (float)y, sliderValue, (float)(y + 2), color);
        RenderUtils.drawFilledCircle((int)sliderValue, y + 1, 3.0f, color);
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + 3 && Mouse.isButtonDown((int)0)) {
            double i = MathHelper.func_151237_a((double)(((double)mouseX - (double)x) / ((double)width - 3.0)), (double)0.0, (double)1.0);
            BigDecimal bigDecimal = new BigDecimal(Double.toString((double)min + (double)(max - min) * i));
            bigDecimal = bigDecimal.setScale(2, 4);
            return bigDecimal.floatValue();
        }
        return value;
    }

    @Override
    public void drawPanel(int mouseX, int mouseY, Panel panel) {
        RenderUtils.newDrawRect(panel.getX(), (float)panel.getY() + 16.0f, (float)panel.getX() + (float)panel.getWidth(), (float)panel.getY() - 16.0f, new Color(231, 229, 230).getRGB());
        RenderUtils.newDrawRect(panel.getX(), (float)panel.getY() - 3.0f, (float)panel.getX() + (float)panel.getWidth(), (float)panel.getY() + 15.0f, new Color(231, 229, 230).getRGB());
        GlStateManager.func_179117_G();
        float textWidth = Fonts.font35.func_78256_a("\u00a7f" + StringUtils.func_76338_a((String)LanguageManager.INSTANCE.get(panel.getName().replaceAll("%", ""))));
        CFontRenderer.DisplayFont(FontLoaders.JELLO30, LanguageManager.INSTANCE.get(panel.getName().replaceAll("%", "")), (float)(panel.getX() + 10), (float)(panel.getY() - 6), new Color(116, 114, 115).getRGB());
    }

    @Override
    public void drawDescription(int mouseX, int mouseY, String text) {
    }

    @Override
    public void drawButtonElement(int mouseX, int mouseY, ButtonElement buttonElement) {
        Gui.func_73734_a((int)(buttonElement.getX() - 1), (int)(buttonElement.getY() - 1), (int)(buttonElement.getX() + buttonElement.getWidth() + 1), (int)(buttonElement.getY() + buttonElement.getHeight() + 1), (int)this.hoverColor(buttonElement.getColor() != Integer.MAX_VALUE ? new Color(14, 159, 255) : new Color(217, 217, 217)).getRGB());
        GlStateManager.func_179117_G();
        Fonts.font35.func_78276_b(LanguageManager.INSTANCE.get(buttonElement.getDisplayName().replaceAll("%", "")), buttonElement.getX() + 5, buttonElement.getY() + 5, Color.BLACK.getRGB());
    }

    @Override
    public void drawModuleElement(int mouseX, int mouseY, ModuleElement moduleElement) {
        Gui.func_73734_a((int)moduleElement.getX(), (int)(moduleElement.getY() - 1), (int)(moduleElement.getX() + moduleElement.getWidth()), (int)(moduleElement.getY() + moduleElement.getHeight() + 1), (int)new Color(250, 250, 250).getRGB());
        Gui.func_73734_a((int)moduleElement.getX(), (int)(moduleElement.getY() - 1), (int)(moduleElement.getX() + moduleElement.getWidth()), (int)(moduleElement.getY() + moduleElement.getHeight() + 1), (int)this.hoverColor(new Color(100, 165, 241, moduleElement.slowlyFade)).getRGB());
        GlStateManager.func_179117_G();
        boolean colour = false;
        if (moduleElement.getModule().getState()) {
            CFontRenderer.DisplayFont(FontLoaders.JELLO20, "    " + LanguageManager.INSTANCE.get(moduleElement.getDisplayName().replaceAll("%", "")), (float)(moduleElement.getX() + 5), (float)(moduleElement.getY() + 3), Color.WHITE.getRGB());
        } else {
            CFontRenderer.DisplayFont(FontLoaders.JELLO20, "  " + LanguageManager.INSTANCE.get(moduleElement.getDisplayName().replaceAll("%", "")), (float)(moduleElement.getX() + 5), (float)(moduleElement.getY() + 3), Color.BLACK.getRGB());
        }
        List<Value<?>> moduleValues = moduleElement.getModule().getValues();
        if (!moduleValues.isEmpty() && moduleElement.isShowSettings()) {
            if (moduleElement.getSettingsWidth() > 0.0f && moduleElement.slowlySettingsYPos > moduleElement.getY() + 6) {
                RenderUtils.newDrawRect(moduleElement.getX() + moduleElement.getWidth() + 4, moduleElement.getY() + 6, (float)(moduleElement.getX() + moduleElement.getWidth()) + moduleElement.getSettingsWidth(), moduleElement.slowlySettingsYPos + 2, Color.white.getRGB());
            }
            moduleElement.slowlySettingsYPos = moduleElement.getY() + 6;
            for (Value<?> value : moduleValues) {
                float textWidth;
                String text;
                if (!value.getDisplayable()) continue;
                if (value instanceof BoolValue) {
                    text = value.getName();
                    textWidth = Fonts.font35.func_78256_a(text);
                    if (moduleElement.getSettingsWidth() < textWidth + 8.0f) {
                        moduleElement.setSettingsWidth(textWidth + 8.0f);
                    }
                    if (mouseX >= moduleElement.getX() + moduleElement.getWidth() + 4 && (float)mouseX <= (float)(moduleElement.getX() + moduleElement.getWidth()) + moduleElement.getSettingsWidth() && mouseY >= moduleElement.slowlySettingsYPos && mouseY <= moduleElement.slowlySettingsYPos + 12 && Mouse.isButtonDown((int)0) && moduleElement.isntPressed()) {
                        BoolValue boolValue;
                        boolValue.set((Boolean)(boolValue = (BoolValue)value).get() == false);
                        mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("gui.button.press"), (float)1.0f));
                    }
                    Fonts.font35.func_78276_b(text, moduleElement.getX() + moduleElement.getWidth() + 6, moduleElement.slowlySettingsYPos + 2, (Boolean)((BoolValue)value).get() != false ? Color.BLACK.getRGB() : new Color(120, 120, 120).getRGB());
                    moduleElement.slowlySettingsYPos += 11;
                    continue;
                }
                if (value instanceof ListValue) {
                    ListValue listValue = (ListValue)value;
                    String text2 = value.getName();
                    float textWidth2 = Fonts.font35.func_78256_a(text2);
                    if (moduleElement.getSettingsWidth() < textWidth2 + 16.0f) {
                        moduleElement.setSettingsWidth(textWidth2 + 16.0f);
                    }
                    Fonts.font35.func_78276_b(text2, moduleElement.getX() + moduleElement.getWidth() + 6, moduleElement.slowlySettingsYPos + 2, -16777216);
                    Fonts.font35.func_78276_b(listValue.openList ? "-" : "+", (int)((float)(moduleElement.getX() + moduleElement.getWidth()) + moduleElement.getSettingsWidth() - (float)(listValue.openList ? 5 : 6)), moduleElement.slowlySettingsYPos + 2, -16777216);
                    if (mouseX >= moduleElement.getX() + moduleElement.getWidth() + 4 && (float)mouseX <= (float)(moduleElement.getX() + moduleElement.getWidth()) + moduleElement.getSettingsWidth() && mouseY >= moduleElement.slowlySettingsYPos && mouseY <= moduleElement.slowlySettingsYPos + Fonts.font35.field_78288_b && Mouse.isButtonDown((int)0) && moduleElement.isntPressed()) {
                        listValue.openList = !listValue.openList;
                        mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("gui.button.press"), (float)1.0f));
                    }
                    moduleElement.slowlySettingsYPos += Fonts.font35.field_78288_b + 1;
                    for (String valueOfList : listValue.getValues()) {
                        float textWidth22 = Fonts.font35.func_78256_a("- " + valueOfList);
                        if (moduleElement.getSettingsWidth() < textWidth22 + 12.0f) {
                            moduleElement.setSettingsWidth(textWidth22 + 12.0f);
                        }
                        if (!listValue.openList) continue;
                        if (mouseX >= moduleElement.getX() + moduleElement.getWidth() + 4 && (float)mouseX <= (float)(moduleElement.getX() + moduleElement.getWidth()) + moduleElement.getSettingsWidth() && mouseY >= moduleElement.slowlySettingsYPos + 2 && mouseY <= moduleElement.slowlySettingsYPos + 14 && Mouse.isButtonDown((int)0) && moduleElement.isntPressed()) {
                            listValue.set(valueOfList);
                            mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("gui.button.press"), (float)1.0f));
                        }
                        GlStateManager.func_179117_G();
                        Fonts.font35.func_78276_b("- " + valueOfList, moduleElement.getX() + moduleElement.getWidth() + 6, moduleElement.slowlySettingsYPos + 2, listValue.get() != null && ((String)listValue.get()).equalsIgnoreCase(valueOfList) ? Color.BLACK.getRGB() : new Color(120, 120, 120).getRGB());
                        moduleElement.slowlySettingsYPos += Fonts.font35.field_78288_b + 1;
                    }
                    if (listValue.openList) continue;
                    ++moduleElement.slowlySettingsYPos;
                    continue;
                }
                if (value instanceof FloatValue) {
                    float valueOfSlide;
                    FloatValue floatValue = (FloatValue)value;
                    String text3 = value.getName() + ": " + this.round(((Float)floatValue.get()).floatValue());
                    float textWidth3 = Fonts.font35.func_78256_a(text3);
                    if (moduleElement.getSettingsWidth() < textWidth3 + 8.0f) {
                        moduleElement.setSettingsWidth(textWidth3 + 8.0f);
                    }
                    if ((valueOfSlide = JelloStyle.drawSlider(((Float)floatValue.get()).floatValue(), floatValue.getMinimum(), floatValue.getMaximum(), moduleElement.getX() + moduleElement.getWidth() + 8, moduleElement.slowlySettingsYPos + 14, (int)moduleElement.getSettingsWidth() - 12, mouseX, mouseY, new Color(7, 152, 252))) != ((Float)floatValue.get()).floatValue()) {
                        floatValue.set(Float.valueOf(valueOfSlide));
                    }
                    Fonts.font35.func_78276_b(text3, moduleElement.getX() + moduleElement.getWidth() + 6, moduleElement.slowlySettingsYPos + 3, -16777216);
                    moduleElement.slowlySettingsYPos += 19;
                    continue;
                }
                if (value instanceof IntegerValue) {
                    float valueOfSlide;
                    IntegerValue integerValue = (IntegerValue)value;
                    String text4 = value.getName() + ": " + (value instanceof BlockValue ? BlockUtils.getBlockName((Integer)integerValue.get()) + " (" + integerValue.get() + ")" : (Serializable)integerValue.get());
                    float textWidth4 = Fonts.font35.func_78256_a(text4);
                    if (moduleElement.getSettingsWidth() < textWidth4 + 8.0f) {
                        moduleElement.setSettingsWidth(textWidth4 + 8.0f);
                    }
                    if ((valueOfSlide = JelloStyle.drawSlider(((Integer)integerValue.get()).intValue(), integerValue.getMinimum(), integerValue.getMaximum(), moduleElement.getX() + moduleElement.getWidth() + 8, moduleElement.slowlySettingsYPos + 14, (int)moduleElement.getSettingsWidth() - 12, mouseX, mouseY, new Color(7, 152, 252))) != (float)((Integer)integerValue.get()).intValue()) {
                        integerValue.set((int)valueOfSlide);
                    }
                    Fonts.font35.func_78276_b(text4, moduleElement.getX() + moduleElement.getWidth() + 6, moduleElement.slowlySettingsYPos + 3, -16777216);
                    moduleElement.slowlySettingsYPos += 19;
                    continue;
                }
                if (value instanceof FontValue) {
                    FontValue fontValue2 = (FontValue)value;
                    FontRenderer fontRenderer = (FontRenderer)fontValue2.get();
                    String displayString = "Font: Unknown";
                    if (fontRenderer instanceof GameFontRenderer) {
                        GameFontRenderer liquidFontRenderer = (GameFontRenderer)fontRenderer;
                        displayString = "Font: " + liquidFontRenderer.getDefaultFont().getFont().getName() + " - " + liquidFontRenderer.getDefaultFont().getFont().getSize();
                    } else if (fontRenderer == Fonts.minecraftFont) {
                        displayString = "Font: Minecraft";
                    } else {
                        Object[] objects = Fonts.getFontDetails(fontRenderer);
                        if (objects != null) {
                            displayString = objects[0] + ((Integer)objects[1] != -1 ? " - " + objects[1] : "");
                        }
                    }
                    Fonts.font35.func_78276_b(displayString, moduleElement.getX() + moduleElement.getWidth() + 6, moduleElement.slowlySettingsYPos + 2, Color.BLACK.getRGB());
                    int stringWidth = Fonts.font35.func_78256_a(displayString);
                    if (moduleElement.getSettingsWidth() < (float)(stringWidth + 8)) {
                        moduleElement.setSettingsWidth(stringWidth + 8);
                    }
                    if ((Mouse.isButtonDown((int)0) && !this.mouseDown || Mouse.isButtonDown((int)1) && !this.rightMouseDown) && mouseX >= moduleElement.getX() + moduleElement.getWidth() + 4 && (float)mouseX <= (float)(moduleElement.getX() + moduleElement.getWidth()) + moduleElement.getSettingsWidth() && mouseY >= moduleElement.slowlySettingsYPos && mouseY <= moduleElement.slowlySettingsYPos + 12) {
                        FontRenderer font;
                        int i;
                        List<FontRenderer> fonts = Fonts.getFonts();
                        if (Mouse.isButtonDown((int)0)) {
                            for (i = 0; i < fonts.size(); ++i) {
                                font = fonts.get(i);
                                if (font != fontRenderer) continue;
                                if (++i >= fonts.size()) {
                                    i = 0;
                                }
                                fontValue2.set(fonts.get(i));
                                break;
                            }
                        } else {
                            for (i = fonts.size() - 1; i >= 0; --i) {
                                font = fonts.get(i);
                                if (font != fontRenderer) continue;
                                if (--i >= fonts.size()) {
                                    i = 0;
                                }
                                if (i < 0) {
                                    i = fonts.size() - 1;
                                }
                                fontValue2.set(fonts.get(i));
                                break;
                            }
                        }
                    }
                    moduleElement.slowlySettingsYPos += 11;
                    continue;
                }
                text = value.getName() + ": " + value.get();
                textWidth = Fonts.font35.func_78256_a(text);
                if (moduleElement.getSettingsWidth() < textWidth + 8.0f) {
                    moduleElement.setSettingsWidth(textWidth + 8.0f);
                }
                GlStateManager.func_179117_G();
                Fonts.font35.func_78276_b(text, moduleElement.getX() + moduleElement.getWidth() + 6, moduleElement.slowlySettingsYPos + 4, -16777216);
                moduleElement.slowlySettingsYPos += 12;
            }
            moduleElement.updatePressed();
            this.mouseDown = Mouse.isButtonDown((int)0);
            this.rightMouseDown = Mouse.isButtonDown((int)1);
        }
    }

    private BigDecimal round(float v) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(v));
        bigDecimal = bigDecimal.setScale(2, 4);
        return bigDecimal;
    }

    private Color hoverColor(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return new Color(Math.max(r, 0), Math.max(g, 0), Math.max(b, 0), color.getAlpha());
    }
}

