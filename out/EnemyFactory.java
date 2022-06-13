public class EnemyFactory {
    private Enemy template;

    public EnemyFactory(Enemy template)
    {
        this.template = template;
    }

    //the where
    public ConcreteEnemy createEnemy(int x, int y)
    {
        ConcreteEnemy enemy = new ConcreteEnemy(x, y, template.getSize(), template.getHp(), template.getMoneyIfDestroyed(), template.getColor());

        EnemyWeapon templateNextWeapon = template.getNextWeapon(), concreteWeapon = null, newWeapon = null;
        //enemy.setNextWeapon(concreteWeapon);

        while(templateNextWeapon != null){
            String name = templateNextWeapon.getWeaponName();

            //create the copy
            if(name.equals("speck"))
                newWeapon = new EnemyWeaponSpeck(templateNextWeapon.getXpos(),templateNextWeapon.getYpos(), enemy,null);
            if(name.equals("sides"))
                newWeapon = new EnemyWeaponSides(templateNextWeapon.getXpos(),templateNextWeapon.getYpos(), enemy,null);

            if(concreteWeapon == null) {
                concreteWeapon = newWeapon;
                enemy.setNextWeapon(newWeapon);
            }
            else {
                concreteWeapon.setNextWeapon(newWeapon);
                concreteWeapon = newWeapon;
            }

            //advance the template and concrete weapons
            templateNextWeapon = templateNextWeapon.getNextWeapon();
        }

        return enemy;
    }
}
