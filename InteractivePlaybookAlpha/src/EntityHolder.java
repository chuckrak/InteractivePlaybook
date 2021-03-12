	import java.awt.Graphics;

public class EntityHolder {
	private Entity entity;
	
	public EntityHolder(Entity e) {
		entity = e;
	}
	public boolean swap(EntityHolder other) {
		if(other.getEntity().getInteractable()) {
			other.getEntity().interact((ControlledDefensivePlayer)entity);
		}
		if(other.getEntity().getMovable()) {
			Entity temp = other.getEntity();
			other.setEntity(entity);
//			System.out.println(other.getEntity());
			entity = temp;
//			System.out.println(entity);
			return true;
		}
		return false;
	}
	public void setEntity(Entity e) {
		entity = e;
	}
	public Entity getEntity() {
		return entity;
	}
}