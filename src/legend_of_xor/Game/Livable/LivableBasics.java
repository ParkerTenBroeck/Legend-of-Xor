package legend_of_xor.Game.Livable;

/**
 *
 * @author Parker
 */
public class LivableBasics {
    private int currentHealth;
    private int maxHealth;
    
    public LivableBasics(int maxHealth, int currentHealth){
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        if(this.currentHealth > this.maxHealth) this.currentHealth = maxHealth;
        else if(this.currentHealth <= 0) this.currentHealth = 1;
        else if(this.maxHealth < 1) this.maxHealth = 1;
    }
    
    public LivableBasics(int maxHealth){
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
        if(this.maxHealth < 1) this.maxHealth = 1;
    }
    
    public void addHealth(int healthAdd){
        currentHealth = currentHealth + healthAdd;
        if(currentHealth > maxHealth) currentHealth = maxHealth;
    }
    
    public void removeHealth(int healthRemove){
        currentHealth = currentHealth - healthRemove;
        if(currentHealth < 0) currentHealth = 0;
    }
    
    public int getCurrentHealth(){
        return currentHealth;
    }
    
    public int getMaxHealth(){
        return maxHealth;
    }
    
    public boolean isDead(){
        return currentHealth > 0 ? false : true;
    }
    
    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
        if(this.maxHealth < 1) this.maxHealth = 1;
        else if(currentHealth > maxHealth) currentHealth = this.maxHealth;
    }
    
    public void setCurrentHealth(int currentHealth){
        this.currentHealth = currentHealth;
        if(this.currentHealth <= 0) this.currentHealth = 0;
        else if (this.currentHealth > maxHealth) this.currentHealth = maxHealth;
    }
}
