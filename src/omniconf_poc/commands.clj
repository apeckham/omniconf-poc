(ns omniconf-poc.commands
  (:require [omniconf.core :as cfg]))

(defn connect []
  (println (format "Connecting to %s!" (cfg/get :hostname))))

(defn disconnect []
  (cfg/with-options [confirm]
    (println (format "Disconnecting! (confirm is %s)" (str confirm)))))

