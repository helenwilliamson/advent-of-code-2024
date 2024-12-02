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
