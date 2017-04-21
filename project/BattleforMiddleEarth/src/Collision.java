/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emintosun
 */
public class Collision {
    Player player;
    Collision(Player player){
        this.player = player;
    }
    
    //assumed player object dimensions are 10,10 and powerup dimensions 10,10
    //assumed given x y upper left corner's coordinates
    //0 0 is upper lest corner of screen
    public boolean isPowerUpTaken(int powerUpX, int powerUpY, int playerX, int playerY){
        if(((powerUpX) <= (playerX+10)) && ((powerUpX+10) >= (playerX)) &&
           ((powerUpY) <= (playerY+10)) && ((powerUpY+10) >= (playerY)))
            return true;
        return false;
    }
    //this method calculates the players damage
    public void calculateDamage(int damage){
        damage = this.player.getWeapon().getAttackDamage();
    }
    //assumed player object dimensions are 10,10 and enemy dimensions 10,10
    //bullet dimensions are 5,5
    //assumed given x y upper left corner's coordinates
    //0 0 is upper lest corner of screen
    public boolean isHit(int bulletX, int bulletY, int objectX, int objectY){
        if(((bulletX) <= (objectX+10)) && ((bulletX+5) >= (objectX)) &&
           ((bulletY) <= (objectY+10)) && ((bulletY+5) >= (objectY)))
            return true;
        return false;
    }
}
