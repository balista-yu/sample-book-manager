package com.book.manager.infrastructure.database.mapper

import com.book.manager.infrastructure.database.mapper.OperatorDynamicSqlSupport.Operator
import com.book.manager.infrastructure.database.mapper.OperatorDynamicSqlSupport.Operator.email
import com.book.manager.infrastructure.database.mapper.OperatorDynamicSqlSupport.Operator.id
import com.book.manager.infrastructure.database.mapper.OperatorDynamicSqlSupport.Operator.name
import com.book.manager.infrastructure.database.mapper.OperatorDynamicSqlSupport.Operator.password
import com.book.manager.infrastructure.database.mapper.OperatorDynamicSqlSupport.Operator.roleType
import com.book.manager.infrastructure.database.record.OperatorRecord
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun OperatorMapper.count(completer: CountCompleter) = countFrom(this::count, Operator, completer)

fun OperatorMapper.delete(completer: DeleteCompleter) = deleteFrom(this::delete, Operator, completer)

fun OperatorMapper.deleteByPrimaryKey(operatorId: Int) = delete { where { id isEqualTo operatorId } }

fun OperatorMapper.insert(record: OperatorRecord) = insert(this::insert, record, Operator) {
    map(id).toProperty("id")
    map(email).toProperty("email")
    map(password).toProperty("password")
    map(name).toProperty("name")
    map(roleType).toProperty("roleType")
}

fun OperatorMapper.insertMultiple(records: Collection<OperatorRecord>) = insertMultiple(
    this::insertMultiple,
    records,
    Operator
) {
    map(id).toProperty("id")
    map(email).toProperty("email")
    map(password).toProperty("password")
    map(name).toProperty("name")
    map(roleType).toProperty("roleType")
}

fun OperatorMapper.insertMultiple(vararg records: OperatorRecord) = insertMultiple((records.toList()))

fun OperatorMapper.insertSelective(record: OperatorRecord) = insert(this::insert, record, Operator) {
    map(id).toPropertyWhenPresent("id", record::id)
    map(email).toPropertyWhenPresent("email", record::email)
    map(password).toPropertyWhenPresent("password", record::password)
    map(name).toPropertyWhenPresent("name", record::name)
    map(roleType).toPropertyWhenPresent("roleType", record::roleType)
}

private val columnList = listOf(id, email, password, name, roleType)

fun OperatorMapper.selectOne(completer: SelectCompleter) = selectOne(this::selectOne, columnList, Operator, completer)

fun OperatorMapper.select(completer: SelectCompleter) = selectList(this::selectMany, columnList, Operator, completer)

fun OperatorMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Operator, completer)

fun OperatorMapper.selectByPrimaryKey(operatorId: Int) = selectOne { where { id isEqualTo operatorId } }

fun OperatorMapper.update(completer: UpdateCompleter) = update(this::update, Operator, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: OperatorRecord) = apply {
    set(id).equalToWhenPresent(record::id)
    set(email).equalToWhenPresent(record::email)
    set(password).equalToWhenPresent(record::password)
    set(name).equalToWhenPresent(record::name)
    set(roleType).equalToWhenPresent(record::roleType)
}

fun KotlinUpdateBuilder.updateSelectiveColumns(record: OperatorRecord) = apply {
    set(id).equalToWhenPresent(record::id)
    set(email).equalToWhenPresent(record::email)
    set(password).equalToWhenPresent(record::password)
    set(name).equalToWhenPresent(record::name)
    set(roleType).equalToWhenPresent(record::roleType)
}

fun OperatorMapper.updateByPrimaryKey(record: OperatorRecord) = update {
    set(email).equalToWhenPresent(record::email)
    set(password).equalToWhenPresent(record::password)
    set(name).equalToWhenPresent(record::name)
    set(roleType).equalToWhenPresent(record::roleType)
    where { id isEqualToWhenPresent record.id }
}

fun OperatorMapper.updateByPrimaryKeySelective(record: OperatorRecord) = update {
    set(email).equalToWhenPresent(record::email)
    set(password).equalToWhenPresent(record::password)
    set(name).equalToWhenPresent(record::name)
    set(roleType).equalToWhenPresent(record::roleType)
    where { id isEqualToWhenPresent record.id }
}
