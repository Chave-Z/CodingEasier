package com.coding.easier.ui.base;

import java.awt.*;

/**
 * @author: D丶Cheng
 * @description:
 * @create: 2019-09-10 12:30
 **/
public class InteractionColors {
    public final Color[] normal;
    public final Color[] rollover;
    public final Color[] pressed;
    public final Color[] disabled;

    public InteractionColors(Color[] normal, Color[] rollover, Color[] pressed, Color[] disabled) {
        this.normal = normal;
        this.rollover = rollover;
        this.pressed = pressed;
        this.disabled = disabled;
    }
}
