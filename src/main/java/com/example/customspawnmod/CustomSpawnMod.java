package com.example.customspawnmod;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;

@Mod("customspawnmod")
public class CustomSpawnMod {
    // Класс для хранения данных о спавне
    private static class MobSpawnData {
        final String mobId;
        final int x1, x2, z1, z2;
        final float chance;

        public MobSpawnData(String mobId, int x1, int x2, int z1, int z2, float chance) {
            this.mobId = mobId;
            this.x1 = x1;
            this.x2 = x2;
            this.z1 = z1;
            this.z2 = z2;
            this.chance = chance;
        }
    }

    // Список всех мобов и их параметров спавна
    private static final List<MobSpawnData> MOB_SPAWNS = Arrays.asList(
            new MobSpawnData("minepiece:kuzan", -200, -100, -200, -100, 1.0f),      // 70% шанс
            new MobSpawnData("trueprimepiecetwo:crocodile", 100, 200, 100, 200, 1.0f), // 50% шанс
            new MobSpawnData("minepiece:corps_marine", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:corps_pirates", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:buchi", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:sham", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:kungfu_jugon", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:buggy", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:benn_beckman", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:yasopp", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:super_penguin", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:helmeppo", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:alvida", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:gin", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:krieg", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:kuro", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:penguin", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:killer_whale", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:shanks", -6100, -4100, 3000, 4000, 1.0f),
            new MobSpawnData("minepiece:zoro_1", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:luffy_1", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:sanji_1", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:dorry", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:brogy", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:mr_13", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:ms_goldenweek", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:corps_pirates", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:crops_marine", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:alvida_2", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:morgan", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:tashigi", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:chu", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:bandit", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:higuma", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:smoker", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:bon_clay", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:ace_1", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("trueprimepiecetwo:crocodile", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("trueprimepiecetwo:portgas_d_ace", -5100, -4100, 5000, 7000, 1.0f),
            new MobSpawnData("minepiece:jonny", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:yosaku", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:charlos", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:coby", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:helmeppo_2", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:corps_marine", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:corps_pirates", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:luffy_1", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:sanji_1", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:zoro_1", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:franky", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:lucci_cp9", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:blueno_cp9", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:jabra_cp9", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:kaku_cp9", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:kalifa_cp9", -8100, -6100, 3000, 4800, 1.0f),
            new MobSpawnData("minepiece:luffy_1", -4000, -3000, 6000, 7000, 1.0f),
            new MobSpawnData("minepiece:zoro_1", -4000, -3000, 6000, 7000, 1.0f),
            new MobSpawnData("minepiece:sanji_1", -4000, -3000, 6000, 7000, 1.0f),
            new MobSpawnData("minepiece:brook", -4000, -3000, 6000, 7000, 1.0f),
            new MobSpawnData("minepiece:franky", -4000, -3000, 6000, 7000, 1.0f),
            new MobSpawnData("minepiece:oars", -4000, -3000, 6000, 7000, 1.0f),
            new MobSpawnData("minepiece:gecko_moria", -4000, -3000, 6000, 7000, 1.0f),
            new MobSpawnData("minepiece:perona", -4000, -3000, 6000, 7000, 1.0f),
            new MobSpawnData("minepiece:kuma", -4000, -3000, 6000, 7000, 1.0f),
            new MobSpawnData("minepiece:kuroobi", -4000, -3000, 6000, 7000, 1.0f),
            new MobSpawnData("minepiece:arlong", -4000, -3000, 6000, 7000, 1.0f),
            new MobSpawnData("minepiece:hatchan", -4000, -3000, 6000, 7000, 1.0f),
            new MobSpawnData("minepiece:bananawani", -4000, -3000, 6000, 7000, 1.0f),
            new MobSpawnData("minepiece:vergo", -11500, -9000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:kuzan", -11500, -9000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:brook", -11500, -9000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:richie", -11500, -9000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:killer", -11500, -9000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:franky_2_years_later", -11500, -9000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:komachiyo", -11500, -9000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:corps_marine", -11500, -9000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:corps_pirates", -11500, -9000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:luffy_2_years_later", -11500, -9000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:zephyr", -11500, -9000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:zoro_2_years_later", -11500, -9000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:sanji_2_years_later", -11500, -9000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:luffy_1", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("trueprimepiecetwo:portgas_d_ace", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("trueprimepiecetwo:kizaru", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("trueprimepiecetwo:whitebeard", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:smoker", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:charlos", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:boa_hancock", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:marco", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:shanks", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:jesus_burgess", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:van_augur", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:monkey_d_garp_2", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:marshall_d_teach", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:corps_marine", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:sentomaru", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:mihawk", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:pacifista", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:ace_1", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:kizaru", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:aokiji", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:akainu", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:yamakaji", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:momonga", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:doll", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:stainless", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:doberman", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:shiryu", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:sengoku", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:hiking_bear", -9000, -6000, 6000, 8000, 1.0f),
            new MobSpawnData("minepiece:sanji_1", -5500, -4000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:luffy_1", -5500, -4000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:zoro_1", -5500, -4000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:franky", -5500, -4000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:silvers_rayleigh", -5500, -4000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:silvers_rayleigh_old", -5500, -4000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:corps_marine", -5500, -4000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:corps_pirates", -5500, -4000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:lucci_cp_9", -5500, -4000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:blueno_cp_9", -5500, -4000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:jabra_cp_9", -5500, -4000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:kaku_cp_9", -5500, -4000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:kalifa_cp_9", -5500, -4000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:sanji_2_years_later", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:luffy_2_years_later", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:zoro_2_years_later", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:brook", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:franky_2_years_later", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:jesus_burgess_2", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:sabo_flame_emperor", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:edward_newgate", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:scopper_gaban_old", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:charlos", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:corps_marine", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:corps_pirates", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:hack", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:koala", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:queen", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:elizabello_ii", -16500, -12500, 4000, 7000, 1.0f),
            new MobSpawnData("minepiece:zoro_2_years_later", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:luffy_2_years_later", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:franky_2_years_later", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:brook", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:sanji_2_years_later", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:shiryu_2", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:van_augur_2", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:lucci_cp_0", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:kaku_cp_0", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:bepo", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:urouge_san", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:sabo", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:fujitora", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:girl_icecream", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("trueprimepiecetwo:enel", -19500, -16500, 4000, 6000, 1.0f),
            new MobSpawnData("minepiece:luffy_2_years_later", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:zoro_2_years_later", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:sanji_onigashima", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:brook", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:franky_2_years_later", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:thatch", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:haruta", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:fossa", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:atmos", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:charlotte_katakuri", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:corps_marine", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:corps_pirates", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:chouchou", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:s_shark", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:s_bear", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:s_hawk", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:s_snake", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:biscuit_warrrior", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:charlotte_cracker", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:stussy", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:king", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:jozu", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:vista", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:jack", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:kalgara", -18000, -15000, 7000, 8000, 1.0f),
            new MobSpawnData("minepiece:monkey_d_garp", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("trueprimepiecetwo:kaidou", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:franky", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:brook", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:kaidou", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:kikunojo", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:kawamatsu", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:nekomamushi", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:inuarashi", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:denjiro", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:kinemon", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:scratchmen_apoo", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:ashura_doji", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:shamrock", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:akainu_fleet_admira", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:corps_marine", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:corps_pirates", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:shanks_four_emperors", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:marshall_d_teach_four_emperors", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:jinbe", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:luffy_wano_country", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:yamato", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:zoro_onigashima", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:ulti", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:page_one", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:benn_beckman_2", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:yasopp_2", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:sanji_onigashima", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:suzume", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:izo", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:hongo_2", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:kingdew", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:rakuyo", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:namur", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:curiel", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:gol_d_roger", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("minepiece:uta", -26500, -20500, 3000, 7000, 1.0f),
            new MobSpawnData("trueprimepiecetwo:laboon", 1000000, -100000000, 1000, 2000, 1.0f),
            new MobSpawnData("trueprimepiecetwo:momoo", 1000000, -100000000, 1000, 2000, 1.0f),
            new MobSpawnData("trueprimepiecetwo:lord_of_the_coast", 1000000, -100000000, 1000, 2000, 1.0f),
            new MobSpawnData("trueprimepiecetwo:lord_of_the_coast", 1000000, -100000000, 8000, 9000, 1.0f),
            new MobSpawnData("trueprimepiecetwo:momoo", 1000000, -100000000, 8000, 9000, 1.0f)

    );

    public CustomSpawnMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.LevelTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.level instanceof ServerLevel) {
            ServerLevel world = (ServerLevel) event.level;

            if (world.getGameTime() % 100 == 0) {
                spawnMobs(world);
            }
        }
    }

    private void spawnMobs(ServerLevel world) {
        for (MobSpawnData data : MOB_SPAWNS) {
            // Проверяем шанс спавна
            if (world.random.nextFloat() < data.chance) {
                ResourceLocation mobId = ResourceLocation.parse(data.mobId);
                EntityType<?> mobType = ForgeRegistries.ENTITY_TYPES.getValue(mobId);

                if (mobType != null) {
                    spawnEntityInZone(mobType, world, data.x1, data.x2, data.z1, data.z2);
                }
            }
        }
    }

    private void spawnEntityInZone(EntityType<?> entityType, ServerLevel world,
                                   int x1, int x2, int z1, int z2) {
        int x = world.random.nextInt(Math.abs(x2 - x1)) + Math.min(x1, x2);
        int z = world.random.nextInt(Math.abs(z2 - z1)) + Math.min(z1, z2);
        int y = world.getHeight(Heightmap.Types.WORLD_SURFACE, x, z);

        Entity entity = entityType.create(world);
        if (entity != null) {
            entity.moveTo(x + 0.5, y, z + 0.5,
                    world.random.nextFloat() * 360.0F, 0.0F);
            world.addFreshEntity(entity);
        }
    }
}