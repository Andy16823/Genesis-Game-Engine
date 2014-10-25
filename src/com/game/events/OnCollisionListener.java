package com.game.events;

import com.physik.collider.CollisionInformation;

public interface OnCollisionListener {

	void OnCollision(Object sender, CollisionInformation arg0);
	
}
