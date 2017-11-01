(ns sqlite-test.db
  (:require [mount.core :as mount]
            [hugsql.core :as hugsql]
            [clojure.java.jdbc :as jdbc]))

;;(def spec
;;  {:classname "org.sqlite.JDBC"
;;   :subprotocol "sqlite"
;;   :subname ":memory:"})

(def db-uri "jdbc:sqlite::memory:")

(declare db)

(defn on-start []
  (let [spec {:connection-uri db-uri}
        conn (jdbc/get-connection spec)]
    (assoc spec :connection conn)))

(defn on-stop []
  (-> db :connection .close)
  nil)

(mount/defstate
  ^{:on-reload :noop}
  db
  :start (on-start)
  :stop (on-stop))

(defn mount-start []
  (mount/start #'db))

(defn mount-stop []
  (mount/stop #'db))
