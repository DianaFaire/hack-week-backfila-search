package app.cash.backfila.service.persistence

import misk.hibernate.Constraint
import misk.hibernate.Id
import misk.hibernate.Operator
import misk.hibernate.Order
import misk.hibernate.Query
import java.time.Instant

interface BackfillRunQuery : Query<DbBackfillRun> {
  @Constraint("id")
  fun id(id: Id<DbBackfillRun>): BackfillRunQuery

  @Constraint("service_id")
  fun serviceId(serviceId: Id<DbService>): BackfillRunQuery

  @Constraint("service_id", Operator.IN)
  fun serviceIdIn(serviceIds: Collection<Id<DbService>>): BackfillRunQuery

  @Constraint("state")
  fun state(state: BackfillState): BackfillRunQuery

  @Constraint("registered_backfill.name")
  fun backfillName(name: String): BackfillRunQuery

  @Constraint("state", Operator.NE)
  fun stateNot(state: BackfillState): BackfillRunQuery

  @Constraint("created_at", Operator.GE)
  fun createdAfter(instant: Instant): BackfillRunQuery

  @Constraint("created_at", Operator.LE)
  fun createdBefore(instant: Instant): BackfillRunQuery

  @Order("id", asc = false)
  fun orderByIdDesc(): BackfillRunQuery
}
