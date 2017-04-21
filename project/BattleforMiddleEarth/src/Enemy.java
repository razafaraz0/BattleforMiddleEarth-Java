/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emintosun
 */
enum EnemyType {
    ork1, 
    ork2, 
    ork3, 
    ork4, 
    boss
}

public class Enemy {
    int health;
    EnemyType enemyType;
    int attackDamage;
    Enemy(EnemyType eT){
        this.health = 100;
        this.enemyType = eT;
    }
    //bi sonraki atisin ne zaman olacagini belirlemek icin integer donuyor
    public int randomFire(){
        if(this.enemyType == EnemyType.ork1){
            return (int)(Math.random()*5+5);
        } else if(this.enemyType == EnemyType.ork2){
            return (int)(Math.random()*5+4);
        } else if(this.enemyType == EnemyType.ork3){
            return (int)(Math.random()*5+3);
        } else if(this.enemyType == EnemyType.ork4){
            return (int)(Math.random()*5+2);
        } else {
            return (int)(Math.random()*3+1);
        }
    }
    // hasar miktarini belirliyor
    public void setAttackDamage(){
        if(this.enemyType == EnemyType.ork1){
            this.attackDamage = 5;
        } else if(this.enemyType == EnemyType.ork2){
            this.attackDamage = 7;
        } else if(this.enemyType == EnemyType.ork3){
            this.attackDamage = 9;
        } else if(this.enemyType == EnemyType.ork4){
            this.attackDamage = 11;
        } else {
            this.attackDamage = 15;
        }
    }
}
