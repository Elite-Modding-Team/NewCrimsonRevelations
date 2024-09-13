package mod.icarus.crimsonrevelations.init;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.IRarity;

public class CRRarities {
    public static final IRarity RARITY_FIREY = new IRarity() {
        @Override
        public String getName() {
            return "Firey";
        }

        @Override
        public TextFormatting getColor() {
            return TextFormatting.DARK_RED;
        }
    };
    
    public static final IRarity RARITY_KNOWLEDGE = new IRarity() {
        @Override
        public String getName() {
            return "Knowledge";
        }

        @Override
        public TextFormatting getColor() {
            return TextFormatting.GREEN;
        }
    };
}
