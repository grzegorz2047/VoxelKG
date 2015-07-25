package pl.voxelkg.center.gameobject;

/**
 * Created by Acer pc on 2015-07-25.
 */
public class GameObject {
    // the collider
    public final Box boxCollider;

    // other GameObject specific data

    public GameObject(int x, int y, int z) {
        boxCollider = new Box(x, y, z);
    }
}
