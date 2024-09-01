/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.Donee;

/**
 *
 * @author Christopher Yap Jian Xing
 */
public class DoneeStateCount {

    private final int johorCount;
    private final int kedahCount;
    private final int kelantanCount;
    private final int melakaCount;
    private final int negeriSembilanCount;
    private final int pahangCount;
    private final int penangCount;
    private final int perakCount;
    private final int perlisCount;
    private final int sabahCount;
    private final int sarawakCount;
    private final int selangorCount;
    private final int terengganuCount;

    public DoneeStateCount(int johorCount, int kedahCount, int kelantanCount, int melakaCount, int negeriSembilanCount, int pahangCount, int penangCount, int perakCount, int perlisCount, int sabahCount, int sarawakCount, int selangorCount, int terengganuCount) {
        this.johorCount = johorCount;
        this.kedahCount = kedahCount;
        this.kelantanCount = kelantanCount;
        this.melakaCount = melakaCount;
        this.negeriSembilanCount = negeriSembilanCount;
        this.pahangCount = pahangCount;
        this.penangCount = penangCount;
        this.perakCount = perakCount;
        this.perlisCount = perlisCount;
        this.sabahCount = sabahCount;
        this.sarawakCount = sarawakCount;
        this.selangorCount = selangorCount;
        this.terengganuCount = terengganuCount;
    }

    public int getJohorCount() {
        return johorCount;
    }

    public int getKedahCount() {
        return kedahCount;
    }

    public int getKelantanCount() {
        return kelantanCount;
    }

    public int getMelakaCount() {
        return melakaCount;
    }

    public int getNegeriSembilanCount() {
        return negeriSembilanCount;
    }

    public int getPahangCount() {
        return pahangCount;
    }

    public int getPenangCount() {
        return penangCount;
    }

    public int getPerakCount() {
        return perakCount;
    }

    public int getPerlisCount() {
        return perlisCount;
    }

    public int getSabahCount() {
        return sabahCount;
    }

    public int getSarawakCount() {
        return sarawakCount;
    }

    public int getSelangorCount() {
        return selangorCount;
    }

    public int getTerengganuCount() {
        return terengganuCount;
    }

    @Override
    public String toString() {
        return "Johor           : " + johorCount
                + "\nKedah           : " + kedahCount
                + "\nKelantan        : " + kelantanCount
                + "\nMelaka          : " + melakaCount
                + "\nNegeri Sembilan : " + negeriSembilanCount
                + "\nPahang          : " + pahangCount
                + "\nPenang          : " + penangCount
                + "\nPerak           : " + perakCount
                + "\nPerlis          : " + perlisCount
                + "\nSabah           : " + sabahCount
                + "\nSarawak         : " + sarawakCount
                + "\nSelangor        : " + selangorCount
                + "\nTerengganu      : " + terengganuCount;
    }

}
