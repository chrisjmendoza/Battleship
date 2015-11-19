public enum FireResult 
{
	// return status, a miss
	MISS, 
	// return status, a hit, doesn't sink a ship
	HIT, 
	// return status, a hit, sunk destroyer 
	SUNK_DESTROYER, 
	// return status, a hit, sunk cruiser
	SUNK_CRUISER, 
	// return status, a hit, sunk battleship
	SUNK_BATTLESHIP, 
	// return status, a hit, sunk aircraft carrier
	SUNK_AIRCRAFT_CARRIER
}
