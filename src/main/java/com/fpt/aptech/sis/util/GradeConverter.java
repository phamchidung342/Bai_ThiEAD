package com.fpt.aptech.sis.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GradeConverter {
    
    /**
     * Calculate final score: Grade = 0.3 × score1 + 0.7 × score2
     */
    public static BigDecimal calculateFinalScore(BigDecimal score1, BigDecimal score2) {
        if (score1 == null || score2 == null) {
            return BigDecimal.ZERO;
        }
        
        BigDecimal weightedScore1 = score1.multiply(new BigDecimal("0.3"));
        BigDecimal weightedScore2 = score2.multiply(new BigDecimal("0.7"));
        BigDecimal finalScore = weightedScore1.add(weightedScore2);
        
        return finalScore.setScale(2, RoundingMode.HALF_UP);
    }
    
    /**
     * Convert numerical score to letter grade:
     * 8.0-10: A
     * 6.0-7.9: B
     * 4.0-5.9: D
     * <4.0: F
     */
    public static String convertToGrade(BigDecimal finalScore) {
        if (finalScore == null) {
            return "F";
        }
        
        double score = finalScore.doubleValue();
        
        if (score >= 8.0 && score <= 10.0) {
            return "A";
        } else if (score >= 6.0 && score < 8.0) {
            return "B";
        } else if (score >= 4.0 && score < 6.0) {
            return "D";
        } else {
            return "F";
        }
    }
    
    /**
     * Convert score1 and score2 directly to grade
     */
    public static String convertToGrade(BigDecimal score1, BigDecimal score2) {
        BigDecimal finalScore = calculateFinalScore(score1, score2);
        return convertToGrade(finalScore);
    }
}

