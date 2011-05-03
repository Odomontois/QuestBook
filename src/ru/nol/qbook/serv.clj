(ns ru.nol.qbook.serv
   (:use clojure.contrib.json))
(gen-class 
  :name ru.nol.qbook.serv.BadService
  :prefix "bad-"
  :extends javax.servlet.http.HttpServlet)
(gen-class
  :name ru.nol.qbook.serv.GoodService
  :prefix "good-"
  :extends javax.servlet.http.HttpServlet )

(defn bad-doGet [this req resp] (-> resp .getWriter (.println "Im very Baaaaad Servlet")))
(defn good-doGet [this req resp] (-> resp .getWriter (.println "I'm the best service in the town")))
(defn good-doPost [this req resp] ( write-json { :value (-> req .getReader read-json :value (+ 2 ))} (.getWriter resp)))

