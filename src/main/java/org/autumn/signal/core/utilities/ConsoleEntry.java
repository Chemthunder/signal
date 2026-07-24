package org.autumn.signal.core.utilities;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

/**
 * @author Chemthunder
 */
public record ConsoleEntry(MutableText text, int delay) {
    public ConsoleEntry(String string, int delay) {
        this(Text.literal(string), delay);
    }
}
