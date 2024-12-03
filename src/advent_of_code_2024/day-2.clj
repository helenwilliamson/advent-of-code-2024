(ns advent-of-code-2024.day-2
  (:gen-class)
  (:require [advent-of-code-2024.utils :as utils]
            [clojure.string :as string]))

(defn parse-data
  [name]
  (->> (utils/read-input name)
       (map #(string/split %1 #"\s+"))
       (map #(map (fn [x] (Integer/parseInt x)) %1))))

(defn increasing?
  [levels]
  (= levels (sort levels)))

(defn decreasing?
  [levels]
  (= levels (sort > levels)))

(defn safe-adjacent?
  [levels]
  (not (->> (partition 2 1 levels)
            (map #(abs (apply - %1)))
            (some #(or (> 1 %1) (< 3 %1))))))

(defn safe-levels
  [levels]
  (and (or (increasing? levels)
           (decreasing? levels))
       (safe-adjacent? levels)))

(defn part-1
  [name]
  (->> (parse-data name)
       (filter #(safe-levels %1))
       (count)))

(defn generate-possible-levels
  [initial-level]
  (->> (map (fn [position]
              (let [[first-half [a & second-half]] (split-at position initial-level)]
                (concat first-half second-half)))
            (range (count initial-level)))
       (filter #(safe-levels %1))))

(defn part-2
  [name]
  (->> (parse-data name)
       (map generate-possible-levels)
       (filter #(not (empty? %1)))
       count))
