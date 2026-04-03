package com.game;

import com.models.*;
import com.models.enums.ItemType;
import com.models.enums.Tile;

import java.util.*;

import static com.models.enums.ItemType.*;

public class Board {
    public static final String RESET = "\u001B[0m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_BLUE = "\u001B[44m";

    private int width;
    private int height;
    private Hero perso;

    private Tile[][] tiles;
    private Monster[][] monsters;
    private Seller seller;

    public Board(int width, int height, int nbrMonsters, Hero perso) {
        this.width = width;
        this.height = height;
        this.perso = perso;
        tiles = new Tile[height][width];
        monsters = new Monster[height][width];
        generateTerrain();
        generateSeller();
        generateMonsters(nbrMonsters);
        generateBoss();
    }

    private void generateTerrain() {
        Random rand = new Random();
        for (int i = 0; i < height; i++) {
            Arrays.fill(tiles[i], Tile.GRASS);
        }
        int nbTreeClusters = (width * height) / 25;
        for (int c = 0; c < nbTreeClusters; c++) {
            int cx = rand.nextInt(width);
            int cy = rand.nextInt(height);
            int rayon = 1;
            placeCluster(cx, cy, rayon, Tile.TREE, 0.45f, rand);
        }
        int nbPondClusters = (width * height) / 50;
        for (int c = 0; c < nbPondClusters; c++) {
            int cx = rand.nextInt(width);
            int cy = rand.nextInt(height);
            int rayon = 1;
            placeCluster(cx, cy, rayon, Tile.POND, 0.35f, rand);
        }
        tiles[perso.getPosition().y()][perso.getPosition().x()] = Tile.GRASS;
        ensureAccessibility();
    }

    private void placeCluster(int cx, int cy, int rayon, Tile type, float probabilite, Random rand) {
        for (int dy = -rayon; dy <= rayon; dy++) {
            for (int dx = -rayon; dx <= rayon; dx++) {
                int tx = cx + dx;
                int ty = cy + dy;
                if (tx < 0 || tx >= width || ty < 0 || ty >= height) continue;
                if (rand.nextFloat() < probabilite) {
                    tiles[ty][tx] = type;
                }
            }
        }
    }

    private void ensureAccessibility() {
        boolean[][] visited = new boolean[height][width];
        Queue<Position> queue = new LinkedList<>();
        Position start = perso.getPosition();
        queue.add(start);
        visited[start.y()][start.x()] = true;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Position p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x() + dx[i];
                int ny = p.y() + dy[i];
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    if (!visited[ny][nx] && tiles[ny][nx] == Tile.GRASS) {
                        visited[ny][nx] = true;
                        queue.add(new Position(nx, ny));
                    }
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (tiles[i][j] == Tile.GRASS && !visited[i][j]) {
                    tiles[i][j] = Tile.GRASS;
                }
            }
        }
    }

    public boolean isWalkable(int x, int y) {
        return tiles[y][x] == Tile.GRASS;
    }

    private void generateMonsters(int nbrMonsters) {
        Random rand = new Random();
        for (int i = 0; i < nbrMonsters; i++) {
            int x, y;
            do {
                x = rand.nextInt(width);
                y = rand.nextInt(height);
            } while (
                    !isWalkable(x, y) ||
                            monsters[y][x] != null ||
                            (perso.getPosition().x() == x && perso.getPosition().y() == y) ||
                            (seller.getPosition().x() == x && seller.getPosition().y() == y)
            );
            monsters[y][x] = Utils.randomMonster(false, perso.getLevel());
        }
    }

    private void generateBoss() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width - 3, width);
            y = rand.nextInt(height - 3, height);
        } while (
                !isWalkable(x, y) ||
                        monsters[y][x] != null ||
                        (perso.getPosition().x() == x && perso.getPosition().y() == y) ||
                        (seller.getPosition().x() == x && seller.getPosition().y() == y)
        );
        monsters[y][x] = Utils.randomMonster(true, perso.getLevel());
    }

    private void generateSeller() {
        List<ItemType> items = List.of(POTION, SWORD, ARMOR, HELMET);
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width);
            y = rand.nextInt(height);
        } while (
                !isWalkable(x, y) ||
                        monsters[y][x] != null ||
                        (perso.getPosition().x() == x && perso.getPosition().y() == y)
        );
        seller = new Seller(items);
        seller.setPosition(new Position(x, y));
    }

    public Position getPositionSeller() {
        return seller.getPosition();
    }

    public Seller getSeller() {
        return seller;
    }

    public void display() {
        System.out.println("=================== MAP ===================");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String result;
                if (i == perso.getPosition().y() && j == perso.getPosition().x()) {
                    result = "\u001B[30m" + BG_GREEN + "@ " + RESET;
                } else if (monsters[i][j] instanceof Orc) {
                    result = monsters[i][j].isBoss() ? "\u001B[31m" + BG_GREEN +  "G " +  RESET : BG_GREEN + "  " + RESET;
                } else if (monsters[i][j] instanceof Drake) {
                    result = monsters[i][j].isBoss() ? "\u001B[31m" + BG_GREEN + "D " +  RESET : BG_GREEN + "  " + RESET;
                } else if (monsters[i][j] instanceof Gobelins) {
                    result = monsters[i][j].isBoss() ? "\u001B[31m" + BG_GREEN +  "O " +  RESET : BG_GREEN + "  " + RESET;
                } else if (i == seller.getPosition().y() && j == seller.getPosition().x()) {
                    result = "\u001B[33m" +  BG_GREEN + "$ " + RESET;
                } else {
                    result = switch (tiles[i][j]) {
                        case TREE -> BG_GREEN + "T " + RESET;
                        case POND -> BG_BLUE + "  " + RESET;
                        default -> BG_GREEN + "  " + RESET;
                    };
                }
                System.out.print(result);
            }
            System.out.println();
        }
        System.out.println("===========================================");
    }

    public Monster movePlayer(Scanner sc, char input) {
        int newX = perso.getPosition().x();
        int newY = perso.getPosition().y();
        switch (input) {
            case 'z': newY--; break;
            case 's': newY++; break;
            case 'q': newX--; break;
            case 'd': newX++; break;
        }
        if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
            return null;
        }
        if (!isWalkable(newX, newY)) {
            System.out.println("Vous ne pouvez pas passer par là !");
            sc.nextLine();
            return null;
        }
        perso.setPosition(new Position(newX, newY));
        Monster monster = monsters[newY][newX];
        if (monster != null) {
            monsters[newY][newX] = null;
            return monster;
        }
        return null;
    }
}