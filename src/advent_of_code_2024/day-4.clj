(ns advent-of-code-2024.day-4
  (:gen-class)
  (:require [advent-of-code-2024.utils :as utils]
            [clojure.string :as string]))

(defn parse-data
  [name]
  (->> (utils/read-input name)
       (map #(string/split %1 #""))
       (apply vector)))

(def xmas '("X" "M" "A" "S"))

(def west [[0 0 0 0] [0 -1 -2 -3]])
(def east [[0 0 0 0] [0 1 2 3]])
(def north [[0 -1 -2 -3] [0 0 0 0]])
(def south [[0 1 2 3] [0 0 0 0]])
(def north-west [[0 -1 -2 -3] [0 -1 -2 -3]])
(def north-east [[0 -1 -2 -3] [0 1 2 3]])
(def south-west [[0 1 2 3] [0 -1 -2 -3]])
(def south-east [[0 1 2 3] [0 1 2 3]])

(def mappers [west east north south north-west north-west south-west south-east])

(defn get-values
  [table mapper row-position column-position]
  (let [indices (apply map vector mapper)]
    (->> (map #(vector (+ row-position (first %1))
                       (+ column-position (second %1)))
              indices)
         (filter #(not (or (neg? (first %1))
                           (neg? (second %1))
                           (<= (count (table 0))(first %1))
                           (<= (count table)(second %1)))))
         (map #((table (first %1)) (second %1))))))

(defn positions
  [table]
  (let [rows (range 0 (count table))
        columns (range 0 (count (table 0)))]
    (mapcat (fn [row]
              (map (fn [column]
                     (vector row column))
                   columns))
            rows)))

(defn part-1
  [name]
  (let [table (parse-data name)
        indices (positions table)]
    (->> (mapcat (fn [position]
                   (map (fn [mapper] (get-values table mapper (first position) (second position)))
                        mappers))
                 indices)
         (filter #(= xmas %1))
         count)))
