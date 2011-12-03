package se.anyro.tewonderhack.subsystem;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import se.anyro.tewonderhack.GameView;
import se.anyro.tewonderhack.components.BitmapComponent;
import se.anyro.tewonderhack.components.ColorComponent;
import se.anyro.tewonderhack.components.LayerIndexComponent;
import se.anyro.tewonderhack.components.SpatialComponent;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.wikidot.entitysystems.rdbmsbeta.EntityManager;
import com.wikidot.entitysystems.rdbmsbeta.SubSystem;

public class RenderScaledDrawableSystem implements SubSystem {

    private boolean mClearScreen;
    private EntityManager mEntityManager;
    private GameView mGameView;

    private float mScaleFactor = -1f;

    private Paint mPaint;
    private Matrix mMatrix;

    public RenderScaledDrawableSystem(EntityManager em, GameView gameView) {
        mEntityManager = em;
        mGameView = gameView;
        mPaint = new Paint();
        mPaint.setFilterBitmap(false);
        mMatrix = new Matrix();
    }

    @Override
    public void processOneGameTick(long lastFrameTime) {
        if (!mGameView.available)
            return;

        if (mScaleFactor < 0) {
            mScaleFactor = (float) Math.floor(mGameView.surfaceHeight / 80);
        }

        Canvas canvas = mGameView.getHolder().lockCanvas();
        if (canvas != null) {

            if (mClearScreen) {
                canvas.drawColor(0xff000000);
                mClearScreen = false;
            }

            Set<UUID> renderables = mEntityManager.getAllEntitiesPossessingComponent(LayerIndexComponent.class);

            ArrayList<UUID> layer0 = new ArrayList<UUID>();
            ArrayList<UUID> layer1 = new ArrayList<UUID>();
            ArrayList<UUID> layer2 = new ArrayList<UUID>();

            for (UUID entity : renderables) {
                int layerIndex = mEntityManager.getComponent(entity, LayerIndexComponent.class).layerIndex;
                if (layerIndex == 0)
                    layer0.add(entity);
                else if (layerIndex == 1)
                    layer1.add(entity);
                else if (layerIndex == 2)
                    layer2.add(entity);
            }

            ArrayList<UUID> allLayers = new ArrayList<UUID>();
            allLayers.addAll(layer0);
            allLayers.addAll(layer1);
            allLayers.addAll(layer2);

            mMatrix = new Matrix();
            // _matrix.setScale(_scaleFactor, _scaleFactor);

            canvas.scale(mScaleFactor, mScaleFactor);

            for (UUID renderable : allLayers) {
                SpatialComponent posComponent = mEntityManager.getComponent(renderable, SpatialComponent.class);

                ColorComponent colComponent = null;
                BitmapComponent bmpComponent = null;

                if (mEntityManager.hasComponent(renderable, ColorComponent.class))
                    colComponent = mEntityManager.getComponent(renderable, ColorComponent.class);
                else if (mEntityManager.hasComponent(renderable, BitmapComponent.class))
                    bmpComponent = mEntityManager.getComponent(renderable, BitmapComponent.class);

                if (bmpComponent != null) {
                    // _matrix.setTranslate(posComponent.x, posComponent.y);
                    canvas.drawBitmap(bmpComponent.bitmap, mMatrix, mPaint);
                } else if (colComponent != null) {
                    canvas.drawRect(posComponent.x, posComponent.y, posComponent.getRight(), posComponent.getBottom(),
                            colComponent.paint);
                }
            }

            mGameView.getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void setScaleFactor(float scaleFactor) {
        mScaleFactor = scaleFactor;
        mClearScreen = true;
    }

}
