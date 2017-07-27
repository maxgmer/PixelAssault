package com.brothergamecompany.pixelassault.toweroffence.Other;

/**
 * Created by maxgm_umv4xdu on 18.06.2017.
 */

import android.support.v4.util.Pair;


import java.util.ArrayList;


public class WaveAlgorithm
{

    private boolean[][] table;
    private int rows;
    private int cols;
    private Pair<Integer, Integer> startPos;
    private Pair<Integer, Integer> finishPos;

    public int currentDist;
    private int intGrid[][];

    public WaveAlgorithm(boolean[][] table, Pair<Integer, Integer> startPos, Pair<Integer, Integer> finishPos)
    {
        this.table = table;
        this.cols = table.length;
        this.rows = table[0].length;
        this.startPos = startPos;
        this.finishPos = finishPos;
        currentDist = runWave();
    }

    //returns -1 if there is no way to finish
    public int runWave()
    {
        intGrid = new int[cols][rows];
        int[][] directions = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        ArrayList<Pair<Integer, Integer>> waveEdge = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> newWaveEdge = new ArrayList<>();
        int currentDist = 0;
        for(int i = 0; i < cols; i++)
            for(int j = 0; j < rows; j++)
            {
                intGrid[i][j] = table[i][j] ? Integer.MAX_VALUE : -1;
            }
        waveEdge.add(startPos);
        intGrid[startPos.first][startPos.second] = 0;
        while(true)
        {
            currentDist++;
            for(Pair<Integer, Integer> pos : waveEdge)
            {
                for(int[] dir : directions)
                {
                    Pair<Integer, Integer> newPos = Pair.create(pos.first + dir[0], pos.second + dir[1]);
                    if(isOutOfField(newPos)) continue;
                    if(intGrid[newPos.first][newPos.second] == -1 || intGrid[newPos.first][newPos.second] <= currentDist) continue;
                    intGrid[newPos.first][newPos.second] = currentDist;
                    if(newPos.equals(finishPos)) return currentDist;
                    newWaveEdge.add(newPos);
                }
            }
            if(newWaveEdge.isEmpty()) return -1;
            waveEdge = new ArrayList<Pair<Integer, Integer>>(newWaveEdge);
            newWaveEdge.clear();
        }
    }

    public boolean isOutOfField(Pair<Integer, Integer> p)
    {
        return p.first < 0 || p.first >= cols || p.second < 0 || p.second >= rows;
    }

    public ArrayList<Pair<Integer, Integer>> getPath()
    {
        if(currentDist == -1) {
            return null;
        }
        Pair<Integer, Integer> lastPos = finishPos;
        ArrayList<Pair<Integer, Integer>> path = new ArrayList<>();
        path.add(lastPos);
        while (!(lastPos != null && lastPos.equals(startPos)))
        {
            lastPos = getClosestCells(lastPos);
            path.add(lastPos);
        }
        path = invertOrder(path);
        return path;
    }

    private ArrayList<Pair<Integer, Integer>> invertOrder(ArrayList<Pair<Integer, Integer>> path) {
        ArrayList<Pair<Integer, Integer>> path2 = new ArrayList<>();
        if (path.size() != 0)
        for (int i = path.size() - 1; i >= 0; i--) {
            path2.add(path.get(i));
        }
        return path2;
    }

    private Pair<Integer, Integer> getClosestCells(Pair<Integer, Integer> pos){
        Integer x = pos.first;
        Integer y = pos.second;
        if (x + 1 < cols){
            if (intGrid[x + 1][y] < intGrid[x][y] && intGrid[x + 1][y] != -1){
                return Pair.create(x + 1, y);
            }
        }
        if (x - 1 >= 0){
            if (intGrid[x - 1][y] < intGrid[x][y] && intGrid[x - 1][y] != -1) {
                return Pair.create(x - 1, y);
            }
        }
        if (y + 1 < rows){
            if (intGrid[x][y + 1] < intGrid[x][y] && intGrid[x][y + 1] != -1) {
                return Pair.create(x, y + 1);
            }
        }
        if (y - 1 >= 0){
            if (intGrid[x][y - 1] < intGrid[x][y] && intGrid[x][y - 1] != -1) {
                return Pair.create(x, y - 1);
            }
        }
        return null;
    }
}