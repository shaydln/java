(def constant constantly)
(defn variable [val] #(% val))
(defn abstract [func]
  (fn [& args]
    (fn [hashmap]
      (apply func (map #(% hashmap) args)))))
(def add (abstract +))
(def subtract (abstract -))
(def multiply (abstract *))
(def divide (abstract #(/ (double %1) (double %2))))
(def negate (abstract -))
(def arcTan (abstract #(Math/atan %)))
(def arcTan2 (abstract #(Math/atan2 %1 %2)))
(defn parse [tokens hashmap _constant _variable]
  (cond
    (symbol? tokens) (_variable (str tokens))
    (number? tokens) (_constant tokens)
    :else (apply (hashmap (first tokens)) (mapv #(parse % hashmap _constant _variable) (rest tokens)))))
(def func-hashmap {'+ add '- subtract '* multiply '/ divide 'negate negate 'atan arcTan 'atan2 arcTan2})
(defn parseFunction [string]
  (parse (read-string string) func-hashmap constant variable))
;;----------------------------------------------------------------------------------------------------------------------
(defn Constant [val]
  {:evaluate (constant val)
   :toString (str val)})
(defn Variable [val]
  {:evaluate (variable val)
   :toString (str val)})
(defn Abstract [func sign]
  (fn [& args]
    {:evaluate (apply func (map #(:evaluate %) args))
     :toString (print-str (lazy-seq (cons sign (map #(:toString %) args))))}))
(def Add (Abstract add "+"))
(def Subtract (Abstract subtract "-"))
(def Multiply (Abstract multiply "*"))
(def Divide (Abstract divide "/"))
(def Negate (Abstract negate "negate"))
(def Sinh (Abstract (abstract #(Math/sinh %)) "sinh"))
(def Cosh (Abstract (abstract #(Math/cosh %)) "cosh"))
(defn evaluate [expr hashmap]
  ((:evaluate expr) hashmap))
(defn toString [expr]
  (:toString expr))
(def obj-hashmap {'+ Add '- Subtract '* Multiply '/ Divide 'negate Negate 'sinh Sinh 'cosh Cosh})
(defn parseObject [string]
  (parse (read-string string) obj-hashmap Constant Variable))