package kiloboltgame.framework;

import java.awt.Image;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class Animation {

	private ArrayList frames;
	private int currentFrame;
	private long animTime;
	private long totalDuration;

	public Animation() {
		frames = new ArrayList();
		totalDuration = 0;

		synchronized (this) {
			animTime = 0;
			currentFrame = 0;
		}
	}

	public synchronized void addFrame(Texture banditLeftRun1, long duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(banditLeftRun1, totalDuration));
	}

	public synchronized void update(long elapsedTime) {
		if (frames.size() > 1) {
			animTime += elapsedTime;
			if (animTime >= totalDuration) {
				animTime = animTime % totalDuration;
				currentFrame = 0;

			}

			while (animTime > getFrame(currentFrame).endTime) {
				currentFrame++;

			}
		}
	}

	public synchronized Texture getImage() {
		if (frames.size() == 0) {
			return null;
		} else {
			return getFrame(currentFrame).image;
		}
	}

	private AnimFrame getFrame(int i) {
		return (AnimFrame) frames.get(i);
	}

	private class AnimFrame {

		Texture image;
		long endTime;

		public AnimFrame(Texture banditLeftRun1, long endTime) {
			this.image = banditLeftRun1;
			this.endTime = endTime;
		}
	}
}
