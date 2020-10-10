package com.noudzandbergen.voxic.block;

import com.noudzandbergen.voxic.block.container.BlockContainer;
import com.noudzandbergen.voxic.block.container.Mesher;

// Guess it's not so simple
public class SimpleBlock extends Block implements TexturedBlock {

	private Texture textureTop;
	private Texture textureBottom;
	private Texture textureNorth;
	private Texture textureSouth;
	private Texture textureEast;
	private Texture textureWest;

	@Override
	public void writeTo(Mesher mesher) {

		BlockContainer container = mesher.getBlocks();
		int x = mesher.getX();
		int y = mesher.getY();
		int z = mesher.getZ();

		boolean renderEast = container.getBlock(--x, y, z).isFaceClear(Face.WEST, x, y, z, container);
		boolean renderWest = container.getBlock(x += 2, y, z).isFaceClear(Face.EAST, x--, y, z, container);

		boolean renderBottom = container.getBlock(x, --y, z).isFaceClear(Face.TOP, x, y, z, container);
		boolean renderTop = container.getBlock(x, y += 2, z).isFaceClear(Face.BOTTOM, x, y--, z, container);

		boolean renderNorth = container.getBlock(x, y, --z).isFaceClear(Face.SOUTH, x, y, z, container);
		boolean renderSouth = container.getBlock(x, y, z += 2).isFaceClear(Face.NORTH, x, y, z--, container);

		/*
					   North : Z-
						  |
			East : X- <= [] => X : West
						 |
					South : Z+
		 */

		if (renderEast) mesher.addQuad(
				mesher.addVertex(0f, 1f, 0f, 0, 1, false),	// topNorthEast
				mesher.addVertex(0f, 1f, 1f, 1, 1, false),	// topSouthEast
				mesher.addVertex(0f, 0f, 1f, 1, 0, false),	// bottomSouthEast
				mesher.addVertex(0f, 0f, 0f, 0, 0, false),	// bottomNorthEast
				false
		);
		if (renderWest) mesher.addQuad(
				mesher.addVertex(1f, 1f, 1f, 0, 1, false),	// topSouthWest
				mesher.addVertex(1f, 1f, 0f, 1, 1, false),	// topNorthWest
				mesher.addVertex(1f, 0f, 0f, 1, 0, false),	// bottomNorthWest
				mesher.addVertex(1f, 0f, 1f, 0, 0, false),	// bottomSouthWest
				false
		);

		if (renderBottom) mesher.addQuad(
				mesher.addVertex(1f, 0f, 0f, 0, 1, false),	// bottomNorthEast
				mesher.addVertex(0f, 0f, 0f, 1, 1, false),	// bottomNorthWest
				mesher.addVertex(0f, 0f, 1f, 1, 0, false),	// bottomSouthWest
				mesher.addVertex(1f, 0f, 1f, 0, 0, false),	// bottomSouthEast
				false
		);
		if (renderTop) mesher.addQuad(
				mesher.addVertex(0f, 1f, 0f, 0, 1, false),	// topNorthWest
				mesher.addVertex(1f, 1f, 0f, 1, 1, false),	// topNorthEast
				mesher.addVertex(1f, 1f, 1f, 1, 0, false),	// topSouthEast
				mesher.addVertex(0f, 1f, 1f, 0, 0, false),	// topSouthWest
				false
		);

		if (renderNorth) mesher.addQuad(
				mesher.addVertex(1f, 1f, 0f, 0, 1, false),	// topNorthEast
				mesher.addVertex(0f, 1f, 0f, 1, 1, false),	// topNorthWest
				mesher.addVertex(0f, 0f, 0f, 1, 0, false),	// bottomNorthWest
				mesher.addVertex(1f, 0f, 0f, 0, 0, false),	// bottomNorthEast
				false
		);
		if (renderSouth) mesher.addQuad(
				mesher.addVertex(0f, 1f, 1f, 0, 1, false),	// topSouthWest
				mesher.addVertex(1f, 1f, 1f, 1, 1, false),	// topSouthEast
				mesher.addVertex(1f, 0f, 1f, 1, 0, false),	// bottomSouthEast
				mesher.addVertex(0f, 0f, 1f, 0, 0, false),	// bottomSouthWest
				false
		);

	}

	@Override
	public boolean isFaceClear(Face face, int x, int y, int z, BlockContainer world) {
		return false;
	}

	@Override
	public Texture getTexture(Face face) {
		return null;
	}
}
