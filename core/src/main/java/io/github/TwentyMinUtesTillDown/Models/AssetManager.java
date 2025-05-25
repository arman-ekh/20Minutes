package io.github.TwentyMinUtesTillDown.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {
    private  static Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private static final String smg = "Sprite/SMGStill/SMGStill.png";
    private static final Texture smgTexture = new Texture(smg);


    private static final String blinkingEye0 = "Sprite/T/T_EyeBlink_0.png";
    private static final String blinkingEye2 = "Sprite/T/T_EyeBlink_2.png";
    private static final String blinkingEye1 = "Sprite/T/T_EyeBlink_1.png";

    private static final Texture blinkingEye0_tex = new Texture(blinkingEye0);
    private static final Texture blinkingEye1_tex = new Texture(blinkingEye1);
    private static final Texture blinkingEye2_tex = new Texture(blinkingEye2);

    private static final Animation<Texture> blinkingEye = new Animation<>(1.5f,
        blinkingEye0_tex,
        blinkingEye1_tex,
        blinkingEye2_tex,
        blinkingEye1_tex,
        blinkingEye0_tex
    );

    private static final String proggresBar = "assets/Sprite/T/T_ReloadBar_0.png";
    private static final String proggresBarSlider = "assets/Sprite/T/T_ReloadBar_1.png";

    private static final Texture proggresBar_tex = new Texture(proggresBar);
    private static final Texture proggresBarSlide_tex = new Texture(proggresBarSlider);

    public static Texture getProggresBar_tex(){
        return proggresBar_tex;
    }
    public static Texture getProggresBarSlide_tex(){
        return proggresBarSlide_tex;
    }


    private static String revolver = "Sprite/RevolverStill/RevolverStill.png";
    private static Texture revolver_tex = new Texture(revolver);

    private static String shotGun = "Sprite/GrenadeLauncherStill/GrenadeLauncherStill.png";
    private static Texture shotGun_tex = new Texture(shotGun);

    private static final String bullet = "Sprite/Icon/Icon_FireStarter.png";

    private static final String revolerReoload0 = "Sprite/RevolverReload/RevolverReload_0.png";
    private static final String revolerReoload1 = "Sprite/RevolverReload/RevolverReload_1.png";
    private static final String revolerReoload2 = "Sprite/RevolverReload/RevolverReload_2.png";
    private static final String revolverReload3 = "Sprite/RevolverReload/RevolverReload_3.png";

    private static final Texture revolverReload_tex0 = new Texture(revolerReoload0);
    private static final Texture revolverReload_tex1 = new Texture(revolerReoload1);
    private static final Texture revolverReload_tex2 = new Texture(revolerReoload2);
    private static final Texture revolverReload_tex3 = new Texture(revolverReload3);

    private static final Animation<Texture> revolverReload = new Animation<>(0.5f , revolverReload_tex0,
        revolverReload_tex1 , revolverReload_tex2 , revolverReload_tex3);

    private static final String shotGunReload0 = "Sprite/GrenadeLauncherReload/GrenadeLauncherReload_0.png";
    private static final String shotGunReload1 = "Sprite/GrenadeLauncherReload/GrenadeLauncherReload_1.png";
    private static final String shotGunReload2 = "Sprite/GrenadeLauncherReload/GrenadeLauncherReload_2.png";

    private static final Texture shotGunReloaad0_tex = new Texture(shotGunReload0);
    private static final Texture shotGunReloaad1_tex = new Texture(shotGunReload1);
    private static final Texture shotGunReloaad2_tex = new Texture(shotGunReload2);

    private static final Animation<Texture> shotGunReload = new Animation<>(0.3f , shotGunReloaad0_tex
        ,shotGunReloaad1_tex , shotGunReloaad2_tex );


    private static final String smgReload0 = "Sprite/SMGReload/SMGReload_0.png";
    private static final String smgReload1 = "Sprite/SMGReload/SMGReload_1.png";
    private static final String smgReload2 = "Sprite/SMGReload/SMGReload_2.png";
    private static final String smgReload3 = "Sprite/SMGReload/SMGReload_3.png";


    private static final Texture smgReload0_tex = new Texture(smgReload0);
    private static final Texture smgReload1_tex = new Texture(smgReload1);
    private static final Texture smgReload2_tex = new Texture(smgReload2);
    private static final Texture smgReload3_tex = new Texture(smgReload3);


    private static final Animation<Texture> smgReload = new Animation<>(0.25f , smgReload0_tex ,
        smgReload1_tex , smgReload2_tex , smgReload3_tex);

    private static String character0_idle0 = "Sprite/Idle/Idle_0.png";
    private static final String character0_idle1 = "Sprite/Idle/Idle_1 #8354.png";
    private static final String character0_idle2 = "Sprite/Idle/Idle_2 #8813.png";
    private static final String character0_idle3 = "Sprite/Idle/Idle_3.png";
    private static final String character0_idle4 = "Sprite/Idle/Idle_4.png";
    private static final String character0_idle5 = "Sprite/Idle/Idle_5.png";
    private static final Texture character0_idle0_tex = new Texture(character0_idle0);
    private static final Texture character0_idle1_tex = new Texture(character0_idle1);
    private static final Texture character0_idle2_tex = new Texture(character0_idle2);
    private static final Texture character0_idle3_tex = new Texture(character0_idle3);
    private static final Texture character0_idle4_tex = new Texture(character0_idle4);
    private static final Texture character0_idle5_tex = new Texture(character0_idle5);
    private static final Animation<Texture> character0_idle_frames = new Animation<>(
        0.1f, character0_idle0_tex, character0_idle1_tex
        , character0_idle2_tex, character0_idle3_tex, character0_idle4_tex, character0_idle5_tex);



    private static final String getCharacter0_running0 = "Sprite/Run/Run_0 #8756.png";
    private static final String getCharacter0_running1 = "Sprite/Run/Run_1 #8772.png";
    private static final  String getGetCharacter0_running2 = "Sprite/Run/Run_2.png";
    private static final String getGetCharacter0_running3 = "Sprite/Run/Run_3.png";
    private static final Texture character0_running0_tex = new Texture(getCharacter0_running0);
    private static final Texture character0_running1_tex = new Texture(getCharacter0_running1);
    private static final Texture character0_running2_tex = new Texture(getGetCharacter0_running2);
    private static final Texture character0_running3_tex = new Texture(getGetCharacter0_running3);
    private static final Animation<Texture> charachter0_running_frames = new Animation<>(
        0.1f,character0_running0_tex,character0_running1_tex,
        character0_running2_tex,character0_running3_tex);




    private static final String tileNum0 = "Sprite/T/T_BoneTile.png";
    private static final String tileNum1 = "Sprite/T/T_TileGrass.png";
    private static final Texture tileNum0Tex = new Texture(tileNum0);
    private static final Texture tileNum1Tex = new Texture(tileNum1);



    private static final String brainMonster0 = "Sprite/BrainMonster/BrainMonster_0.png";
    private static final String brainMonster1 = "Sprite/BrainMonster/BrainMonster_1.png";
    private static final String brainMonster2 = "Sprite/BrainMonster/BrainMonster_2.png";
    private static final String brainMonster3 = "Sprite/BrainMonster/BrainMonster_3.png";
    private static final Texture brainMonster0_tex = new Texture(brainMonster0);
    private static final Texture brainMonster1_tex = new Texture(brainMonster1);
    private static final Texture brainMonster2_tex = new Texture(brainMonster2);
    private static final Texture brainMonster3_tex = new Texture(brainMonster3);


    private static final Animation<Texture> brainMonsterMovment = new Animation<>(
        1.5f , brainMonster0_tex , brainMonster1_tex , brainMonster2_tex , brainMonster3_tex);




    private static final String treeMonster0 = "Sprite/T/T_TreeMonster_0.png";
    private static final String treeMonster1 = "Sprite/T/T_TreeMonster_1.png";
    private static final String treeMonster2 = "Sprite/T/T_TreeMonster_2.png";
    private static final Texture treeMonster0_tex = new Texture(treeMonster0);
    private static final Texture treeMonster1_tex = new Texture(treeMonster1);
    private static final Texture treeMonster2_tex = new Texture(treeMonster2);

    private static final Animation<Texture> treeMonster = new Animation<>(
        0.1f , treeMonster0_tex , treeMonster1_tex , treeMonster2_tex);

    private static final String eyeBat0 = "Sprite/EyeMonster/EyeMonster_0.png";
    private static final String eyeBat1 = "Sprite/EyeMonster/EyeMonster_1.png";
    private static final String eyeBat2 = "Sprite/EyeMonster/EyeMonster_2.png";

    private static final Texture eyeBat0_tex = new Texture(eyeBat0);
    private static final Texture eyeBat1_tex = new Texture(eyeBat1);
    private static final Texture eyeBat2_tex = new Texture(eyeBat2);

    private static final Animation<Texture> eyeBat = new Animation<>(2f , eyeBat0_tex , eyeBat1_tex , eyeBat2_tex);



    private static final String deadMonster0 = "Sprite/DeathFX/DeathFX_0.png";
    private static final String deadMonster1 = "Sprite/DeathFX/DeathFX_1.png";
    private static final String deadMonster2 = "Sprite/DeathFX/DeathFX_2.png";
    private static final String deadMonster3 = "Sprite/DeathFX/DeathFX_3.png";

    private static final Texture deadMonster0_tex = new Texture(deadMonster0);
    private static final Texture deadMonster1_tex = new Texture(deadMonster1);
    private static final Texture deadMonster2_tex = new Texture(deadMonster2);
    private static final Texture deadMonster3_tex = new Texture(deadMonster3);

    private static final Animation<Texture> deadMonster = new Animation<>(0.1f , deadMonster0_tex
        ,deadMonster1_tex ,deadMonster2_tex ,deadMonster3_tex );



    private static final String charachter1Running0 = "Sprite/Run/Run_0.png";
    private static final String charachter1Running1 = "Sprite/Run/Run_1.png";
    private static final String charachter1Running2 = "Sprite/Run/Run_2 #8804.png";
    private static final String charachter1Running3 = "Sprite/Run/Run_3 #8720.png";

    private static final Texture charachter1Rinning0_tex = new Texture(charachter1Running0);
    private static final Texture charachter1Rinning1_tex = new Texture(charachter1Running1);
    private static final Texture charachter1Rinning2_tex = new Texture(charachter1Running2);
    private static final Texture charachter1Rinning3_tex = new Texture(charachter1Running3);

    private static final Animation<Texture> charachter1Running = new Animation<>(0.1f
        , charachter1Rinning0_tex , charachter1Rinning1_tex , charachter1Rinning2_tex , charachter1Rinning3_tex);



    private static final String charachter1Idle0 = "Sprite/Idle/Idle_0 #8704.png";
    private static final String charachter1Idle1 = "Sprite/Idle/Idle_1.png";
    private static final String charachter1Idle2 = "Sprite/Idle/Idle_2.png";
    private static final String charachter1Idle3 = "Sprite/Idle/Idle_3 #8750.png";
    private static final String charachter1Idle4 = "Sprite/Idle/Idle_4 #8793.png";
    private static final String charachter1Idle5 = "Sprite/Idle/Idle_5 #8398.png";

    private static final Texture charachter1Idle0_tex = new Texture(charachter1Idle0);
    private static final Texture charachter1Idle1_tex = new Texture(charachter1Idle1);
    private static final Texture charachter1Idle2_tex = new Texture(charachter1Idle2);
    private static final Texture charachter1Idle3_tex = new Texture(charachter1Idle3);
    private static final Texture charachter1Idle4_tex = new Texture(charachter1Idle4);
    private static final Texture charachter1Idle5_tex = new Texture(charachter1Idle5);

    private static final Animation<Texture> charachter1Idle = new Animation<>(0.1f ,
    charachter1Idle0_tex,charachter1Idle1_tex,charachter1Idle2_tex,charachter1Idle3_tex,charachter1Idle4_tex,charachter1Idle5_tex);



    private static final String charachter2Running0 = "Sprite/Run/Run_0 #8758.png";
    private static final String charachter2Running1 = "Sprite/Run/Run_1 #8774.png";
    private static final String charachter2Running2 = "Sprite/Run/Run_2 #8282.png";
    private static final String charachter2Running3 = "Sprite/Run/Run_3 #8345.png";

    private static final String charachter2Idle0 = "Sprite/Idle/Idle_0 #8326.png";
    private static final String charachter2Idle1 = "Sprite/Idle/Idle_1 #8356.png";
    private static final String charachter2Idle2 = "Sprite/Idle/Idle_2 #8815.png";
    private static final String charachter2Idle3 = "Sprite/Idle/Idle_3 #8453.png";
    private static final String charachter2Idle4 = "Sprite/Idle/Idle_4 #8314.png";
    private static final String charachter2Idle5 = "Sprite/Idle/Idle_5 #8303.png";

    private static final Texture charachter2Running0_tex = new Texture(charachter2Running0);
    private static final Texture charachter2Running1_tex = new Texture(charachter2Running1);
    private static final Texture charachter2Running2_tex = new Texture(charachter2Running2);
    private static final Texture charachter2Running3_tex = new Texture(charachter2Running3);


    private static final Animation<Texture> charachter2Running = new Animation<>(0.1f,
        charachter2Running0_tex, charachter2Running1_tex,
        charachter2Running2_tex, charachter2Running3_tex);



    private static final String charachter3Idle0 = "Sprite/Idle/Idle_0 #8333.png";
    private static final String charachter3Idle1 = "Sprite/Idle/Idle_1 #8363.png";
    private static final String charachter3Idle2 = "Sprite/Idle/Idle_2 #8822.png";
    private static final String charachter3Idle3 = "Sprite/Idle/Idle_3 #8460.png";
    private static final String charachter3Idle4 = "Sprite/Idle/Idle_4 #8321.png";
    private static final String charachter3Idle5 = "Sprite/Idle/Idle_5 #8310.png";

    private static final String charachter3Running0 = "Sprite/Run/Run_0 #8765.png";
    private static final String charachter3Running1 = "Sprite/Run/Run_1 #8781.png";
    private static final String charachter3Running2 = "Sprite/Run/Run_2 #8289.png";
    private static final String charachter3Running3 = "Sprite/Run/Run_3 #8352.png";

    private static final Texture charachter3Idle0_tex = new Texture(charachter3Idle0);
    private static final Texture charachter3Idle1_tex = new Texture(charachter3Idle1);
    private static final Texture charachter3Idle2_tex = new Texture(charachter3Idle2);
    private static final Texture charachter3Idle3_tex = new Texture(charachter3Idle3);
    private static final Texture charachter3Idle4_tex = new Texture(charachter3Idle4);
    private static final Texture charachter3Idle5_tex = new Texture(charachter3Idle5);

    private static final Animation<Texture> charachter3Idle = new Animation<>(0.1f,
        charachter3Idle0_tex, charachter3Idle1_tex, charachter3Idle2_tex,
        charachter3Idle3_tex, charachter3Idle4_tex, charachter3Idle5_tex);



    private static final Texture charachter3Running0_tex = new Texture(charachter3Running0);
    private static final Texture charachter3Running1_tex = new Texture(charachter3Running1);
    private static final Texture charachter3Running2_tex = new Texture(charachter3Running2);
    private static final Texture charachter3Running3_tex = new Texture(charachter3Running3);

    private static final Animation<Texture> charachter3Running = new Animation<>(0.1f,
        charachter3Running0_tex, charachter3Running1_tex,
        charachter3Running2_tex, charachter3Running3_tex);




    private static final Texture charachter2Idle0_tex = new Texture(charachter2Idle0);
    private static final Texture charachter2Idle1_tex = new Texture(charachter2Idle1);
    private static final Texture charachter2Idle2_tex = new Texture(charachter2Idle2);
    private static final Texture charachter2Idle3_tex = new Texture(charachter2Idle3);
    private static final Texture charachter2Idle4_tex = new Texture(charachter2Idle4);
    private static final Texture charachter2Idle5_tex = new Texture(charachter2Idle5);

    private static final Animation<Texture> charachter2Idle = new Animation<>(0.1f,
        charachter2Idle0_tex, charachter2Idle1_tex, charachter2Idle2_tex,
        charachter2Idle3_tex, charachter2Idle4_tex, charachter2Idle5_tex);



    private static final String charachter4Idle0 = "Sprite/Idle/Idle_0 #8325.png";
    private static final String charachter4Idle1 = "Sprite/Idle/Idle_1 #8355.png";
    private static final String charachter4Idle2 = "Sprite/Idle/Idle_2 #8814.png";
    private static final String charachter4Idle3 = "Sprite/Idle/Idle_3 #8452.png";
    private static final String charachter4Idle4 = "Sprite/Idle/Idle_4 #8313.png";
    private static final String charachter4Idle5 = "Sprite/Idle/Idle_5 #8302.png";

    private static final String charachter4Running0 = "Sprite/Run/Run_0 #8757.png";
    private static final String charachter4Running1 = "Sprite/Run/Run_1 #8773.png";
    private static final String charachter4Running2 = "Sprite/Run/Run_2 #8281.png";
    private static final String charachter4Running3 = "Sprite/Run/Run_3 #8344.png";


    private static final Texture charachter4Idle0_tex = new Texture(charachter4Idle0);
    private static final Texture charachter4Idle1_tex = new Texture(charachter4Idle1);
    private static final Texture charachter4Idle2_tex = new Texture(charachter4Idle2);
    private static final Texture charachter4Idle3_tex = new Texture(charachter4Idle3);
    private static final Texture charachter4Idle4_tex = new Texture(charachter4Idle4);
    private static final Texture charachter4Idle5_tex = new Texture(charachter4Idle5);

    private static final Animation<Texture> charachter4Idle = new Animation<>(0.1f,
        charachter4Idle0_tex, charachter4Idle1_tex, charachter4Idle2_tex,
        charachter4Idle3_tex, charachter4Idle4_tex, charachter4Idle5_tex);



    private static final Texture charachter4Running0_tex = new Texture(charachter4Running0);
    private static final Texture charachter4Running1_tex = new Texture(charachter4Running1);
    private static final Texture charachter4Running2_tex = new Texture(charachter4Running2);
    private static final Texture charachter4Running3_tex = new Texture(charachter4Running3);

    private static final Animation<Texture> charachter4Running = new Animation<>(0.1f,
        charachter4Running0_tex, charachter4Running1_tex,
        charachter4Running2_tex, charachter4Running3_tex);



    private static final String subNiggut0 = "Sprite/T/T_ShubNiggurath_0.png";
    private static final String subNiggut1 = "Sprite/T/T_ShubNiggurath_1.png";
    private static final String subNiggut2 = "Sprite/T/T_ShubNiggurath_2.png";
    private static final String subNiggut3 = "Sprite/T/T_ShubNiggurath_3.png";
    private static final String subNiggut4 = "Sprite/T/T_ShubNiggurath_4.png";
    private static final String subNiggut5 = "Sprite/T/T_ShubNiggurath_5.png";
    private static final String subNiggut6 = "Sprite/T/T_ShubNiggurath_6.png";
    private static final String subNiggut7 = "Sprite/T/T_ShubNiggurath_7.png";
    private static final String subNiggut8 = "Sprite/T/T_ShubNiggurath_8.png";
    private static final String subNiggut9 = "Sprite/T/T_ShubNiggurath_9.png";
    private static final String subNiggut10 = "Sprite/T/T_ShubNiggurath_10.png";

    private static final Texture subNiggut0_tex = new Texture(subNiggut0);
    private static final Texture subNiggut1_tex = new Texture(subNiggut1);
    private static final Texture subNiggut2_tex = new Texture(subNiggut2);
    private static final Texture subNiggut3_tex = new Texture(subNiggut3);
    private static final Texture subNiggut4_tex = new Texture(subNiggut4);
    private static final Texture subNiggut5_tex = new Texture(subNiggut5);
    private static final Texture subNiggut6_tex = new Texture(subNiggut6);
    private static final Texture subNiggut7_tex = new Texture(subNiggut7);
    private static final Texture subNiggut8_tex = new Texture(subNiggut8);
    private static final Texture subNiggut9_tex = new Texture(subNiggut9);
    private static final Texture subNiggut10_tex = new Texture(subNiggut10);


    private static final String heartLvlUp = "Sprite/HeartAnimation/HeartAnimation_0.png";
    private static final Texture heartLvlUp_tex = new Texture(heartLvlUp);

    public static Texture getHeartLvlUp_tex(){
        return heartLvlUp_tex;
    }

    private static final String damageLvlUp = "Sprite/Icon/Icon_Berserk.png";
    private static final Texture damageLvlUp_tex = new Texture(damageLvlUp);

    public static Texture getDamageLvlUp_tex(){
        return damageLvlUp_tex;
    }

    private static final String projectileLvlUp = "Sprite/Icon/Icon_Bullet_Storm.png";
    private static final Texture projectileLvlUp_tex = new Texture(projectileLvlUp);

    public static Texture getProjectileLvlUp_tex(){
        return projectileLvlUp_tex;
    }

    private static final String speedLvlUp = "Sprite/Icon/Icon_Haste.png";
    private static final Texture speedLvlUp_tex = new Texture(speedLvlUp);

    public static Texture getSpeedLvlUp_tex(){
        return speedLvlUp_tex;
    }

    private static final String maxAmmoLvlUp = "Sprite/Icon/Icon_QuickHands.png";
    private static final Texture maxAmmoLvlUp_tex = new Texture(maxAmmoLvlUp);

    public static Texture getMaxAmmoLvlUp_tex(){
        return maxAmmoLvlUp_tex;
    }

    public static Texture getSubNiggetTEx(){
        return subNiggut0_tex;
    }

    public static final Animation<Texture> subNiggutRunning = new Animation<>(1.5f
        , subNiggut0_tex , subNiggut1_tex , subNiggut2_tex , subNiggut3_tex);

    private static final Animation<Texture> subNiggutDashing = new Animation<>(1f , subNiggut4_tex
        , subNiggut5_tex , subNiggut6_tex , subNiggut7_tex , subNiggut8_tex , subNiggut9_tex , subNiggut10_tex);


    public static Animation<Texture> getSubNiggutDashing(){
        return subNiggutDashing;
    }

    private static final String Xp = "Sprite/HitImpactFX/HitImpactFX_0.png";
    private static final Texture Xp_tex = new Texture(Xp);

    public static Texture getXp(){
        return Xp_tex;
    }


    public static Skin getSkin() {
        return skin;
    }
    public static Texture getSmgTexture(){
        return smgTexture;
    }

    public static Texture getCharacter0_idle0(){
        return character0_idle0_tex;
    }
    public static Texture getCharacter1_idle0(){
        return charachter1Idle0_tex;
    }
    public static Texture getCharacter2_idle0(){
        return charachter1Idle2_tex;
    }
    public static Texture getCharacter3_idle0(){
        return charachter1Idle3_tex;
    }
    public static Texture getCharacter4_idle0(){
        return charachter1Idle4_tex;
    }

    public static Animation<Texture> getCharacter0_idle_animation() {
        return character0_idle_frames;
    }

    public static Animation<Texture> getCharachter0_running_frames(){return charachter0_running_frames;}

    public static String getSmg(){
        return smg;
    }

    public static String getBullet(){
        return bullet;
    }

    public static Texture getTileNum0Tex(){
        return tileNum0Tex;
    }
    public static Texture getTileNum1Tex(){
        return tileNum1Tex;
    }

    public static Texture getBrainMonster0_tex(){
        return brainMonster0_tex;
    }
    public static Texture getRevolver_tex() {
        return revolver_tex;
    }
    public static Animation<Texture> getBrainMonsterMovment(){
        return brainMonsterMovment;
    }
    public static Texture getEyeBat_tex(){
        return eyeBat0_tex;
    }
    public static Animation<Texture> getTreeMonsterMovment() {
        return treeMonster;
    }
    public static Texture getTreeMonsterTex(){
        return treeMonster0_tex;
    }
    public static Animation<Texture> getEyeBatMovement(){
        return eyeBat;
    }
    public static Animation<Texture> getDeadMonster() {
        return deadMonster;
    }
    public static Animation<Texture> getCharachter1Running(){
        return charachter1Running;
    }
    public static Animation<Texture> getCharachter1Idle(){
        return charachter1Idle;
    }
    public static Animation<Texture> getCharachter2Running() {
        return charachter2Running;
    }
    public static Animation<Texture> getCharachter4Idle() {
        return charachter4Idle;
    }
    public static Animation<Texture> getCharachter3Idle() {
        return charachter3Idle;
    }
    public static Animation<Texture> getCharachter3Running() {
        return charachter3Running;
    }
    public static Animation<Texture> getCharachter2Idle() {
        return charachter2Idle;
    }
    public static Animation<Texture> getCharachter4Running() {
        return charachter4Running;
    }

    public static Texture getShotGun_tex() {
        return shotGun_tex;
    }

    public static Animation<Texture> getShotGunReload(){
        return shotGunReload;
    }

    public static Animation<Texture> getRevolverReload(){
        return revolverReload;
    }

    public static Animation<Texture> getSmgReload(){
        return smgReload;
    }
    public static Animation<Texture> getBlinkingEye(){
        return blinkingEye;
    }
    private static BitmapFont font;

    public static void load() {
        font = new BitmapFont(Gdx.files.internal("skin/bitmap_font.fnt"));
    }

    public static BitmapFont getFont() {
        return font;
    }
}
