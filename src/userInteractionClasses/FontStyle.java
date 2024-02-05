package userInteractionClasses;

import java.awt.*;

public enum FontStyle {
    BUTTON_FONT(new Font("SansSerif", Font.BOLD, 15)),
    TITLE_FONT(new Font("SansSerif", Font.BOLD, 50)),
    LABEL_FONT_PLAIN(new Font("SansSerif", Font.PLAIN, 20)),
    LABEL_FONT_BOLD(new Font("SansSerif", Font.BOLD, 20)),
    CREDIT_FONT(new Font("SansSerif", Font.BOLD, 10));

    private final Font font;

    FontStyle(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
}
