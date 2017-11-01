-- :name get-user-visits :?
select
    v.mark,
    v.visited_at,
    l.place
from visits v
join locations l on v.location = l.id
where
    v.user = :user_id
    /*~ (when (:fromDate params) */
    and v.visited_at > :fromDate
    /*~ ) ~*/
    /*~ (when (:toDate params) */
    and v.visited_at < :toDate
    /*~ ) ~*/
    /*~ (when (:toDistance params) */
    and l.distance < :toDistance
    /*~ ) ~*/
    /*~ (when (:country params) */
    and l.country = :country
    /*~ ) ~*/
order by
    v.visited_at;

-- :name create-user-table :!
create table user (
    id INTEGER PRIMARY KEY,
	name text
)

