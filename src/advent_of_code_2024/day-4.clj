(ns advent-of-code-2024.day-4
  (:gen-class)
  (:require [advent-of-code-2024.utils :as utils]
            [clojure.string :as string]))

(defn parse-data
  [name]
  (->> (utils/read-input name)
       (map #(string/split %1 #""))
       (apply vector)))

(defn left
  [row, position]
  (= (let [[values] (split-at (+ 1 position) row)]
       (take 4 (reverse values)))
     '("X" "M" "A" "S")))

(defn right
  [row, position]
  (= (let [[a values] (split-at position row)]
       (take 4 values))
     '("X" "M" "A" "S")))

(defn up
  [table row-position column-position]
  (let [indices (drop-while neg? (range (- row-position 3) (+ 1 row-position)))]
    (= (->> (map #((data %1) column-position) indices)
            reverse)
       '("X" "M" "A" "S"))))
