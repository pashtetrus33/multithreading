package com.flamexander.multithreading.p6_locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RRWLocksApp {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

        new Thread(() -> {
            rwl.readLock().lock();
            System.out.println("READING-start-a");
            try {
                Thread.sleep(3000);
                System.out.println("READING-end-a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwl.readLock().unlock();
            }
        }).start();

        new Thread(() -> {
            rwl.readLock().lock();
            System.out.println("READING-start-b");
            try {
                Thread.sleep(5000);
                System.out.println("READING-end-b");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwl.readLock().unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rwl.readLock().lock();
            try {
                System.out.println("READING-start-c");
                Thread.sleep(3000);
                System.out.println("READING-end-c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwl.readLock().unlock();
            }
        }).start();

        new Thread(() -> {
            rwl.writeLock().lock();
            System.out.println("WRITING-start-a");
            try {
                Thread.sleep(3000);
                System.out.println("WRITING-end-a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwl.writeLock().unlock();
            }
        }).start();

        new Thread(() -> {
            rwl.writeLock().lock();
            System.out.println("WRITING-start-b");
            try {
                Thread.sleep(3000);
                System.out.println("WRITING-end-b");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwl.writeLock().unlock();
            }
        }).start();
    }
}
