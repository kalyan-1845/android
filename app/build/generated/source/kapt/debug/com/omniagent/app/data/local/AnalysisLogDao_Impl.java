package com.omniagent.app.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.omniagent.app.core.model.AnalysisLog;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AnalysisLogDao_Impl implements AnalysisLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AnalysisLog> __insertionAdapterOfAnalysisLog;

  private final EntityDeletionOrUpdateAdapter<AnalysisLog> __deletionAdapterOfAnalysisLog;

  private final SharedSQLiteStatement __preparedStmtOfClearAllLogs;

  public AnalysisLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAnalysisLog = new EntityInsertionAdapter<AnalysisLog>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `analysis_logs` (`id`,`timestamp`,`userInput`,`classifiedModule`,`confidence`,`confidenceLevel`,`resultJson`,`reasoningJson`,`userRole`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AnalysisLog entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTimestamp());
        if (entity.getUserInput() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getUserInput());
        }
        if (entity.getClassifiedModule() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getClassifiedModule());
        }
        statement.bindDouble(5, entity.getConfidence());
        if (entity.getConfidenceLevel() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getConfidenceLevel());
        }
        if (entity.getResultJson() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getResultJson());
        }
        if (entity.getReasoningJson() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getReasoningJson());
        }
        if (entity.getUserRole() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getUserRole());
        }
      }
    };
    this.__deletionAdapterOfAnalysisLog = new EntityDeletionOrUpdateAdapter<AnalysisLog>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `analysis_logs` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AnalysisLog entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfClearAllLogs = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM analysis_logs";
        return _query;
      }
    };
  }

  @Override
  public Object insertLog(final AnalysisLog log, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfAnalysisLog.insertAndReturnId(log);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLog(final AnalysisLog log, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAnalysisLog.handle(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAllLogs(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAllLogs.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearAllLogs.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AnalysisLog>> getAllLogs() {
    final String _sql = "SELECT * FROM analysis_logs ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"analysis_logs"}, new Callable<List<AnalysisLog>>() {
      @Override
      @NonNull
      public List<AnalysisLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUserInput = CursorUtil.getColumnIndexOrThrow(_cursor, "userInput");
          final int _cursorIndexOfClassifiedModule = CursorUtil.getColumnIndexOrThrow(_cursor, "classifiedModule");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfResultJson = CursorUtil.getColumnIndexOrThrow(_cursor, "resultJson");
          final int _cursorIndexOfReasoningJson = CursorUtil.getColumnIndexOrThrow(_cursor, "reasoningJson");
          final int _cursorIndexOfUserRole = CursorUtil.getColumnIndexOrThrow(_cursor, "userRole");
          final List<AnalysisLog> _result = new ArrayList<AnalysisLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnalysisLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpUserInput;
            if (_cursor.isNull(_cursorIndexOfUserInput)) {
              _tmpUserInput = null;
            } else {
              _tmpUserInput = _cursor.getString(_cursorIndexOfUserInput);
            }
            final String _tmpClassifiedModule;
            if (_cursor.isNull(_cursorIndexOfClassifiedModule)) {
              _tmpClassifiedModule = null;
            } else {
              _tmpClassifiedModule = _cursor.getString(_cursorIndexOfClassifiedModule);
            }
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final String _tmpConfidenceLevel;
            if (_cursor.isNull(_cursorIndexOfConfidenceLevel)) {
              _tmpConfidenceLevel = null;
            } else {
              _tmpConfidenceLevel = _cursor.getString(_cursorIndexOfConfidenceLevel);
            }
            final String _tmpResultJson;
            if (_cursor.isNull(_cursorIndexOfResultJson)) {
              _tmpResultJson = null;
            } else {
              _tmpResultJson = _cursor.getString(_cursorIndexOfResultJson);
            }
            final String _tmpReasoningJson;
            if (_cursor.isNull(_cursorIndexOfReasoningJson)) {
              _tmpReasoningJson = null;
            } else {
              _tmpReasoningJson = _cursor.getString(_cursorIndexOfReasoningJson);
            }
            final String _tmpUserRole;
            if (_cursor.isNull(_cursorIndexOfUserRole)) {
              _tmpUserRole = null;
            } else {
              _tmpUserRole = _cursor.getString(_cursorIndexOfUserRole);
            }
            _item = new AnalysisLog(_tmpId,_tmpTimestamp,_tmpUserInput,_tmpClassifiedModule,_tmpConfidence,_tmpConfidenceLevel,_tmpResultJson,_tmpReasoningJson,_tmpUserRole);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<AnalysisLog>> getRecentLogs(final int limit) {
    final String _sql = "SELECT * FROM analysis_logs ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"analysis_logs"}, new Callable<List<AnalysisLog>>() {
      @Override
      @NonNull
      public List<AnalysisLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUserInput = CursorUtil.getColumnIndexOrThrow(_cursor, "userInput");
          final int _cursorIndexOfClassifiedModule = CursorUtil.getColumnIndexOrThrow(_cursor, "classifiedModule");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfResultJson = CursorUtil.getColumnIndexOrThrow(_cursor, "resultJson");
          final int _cursorIndexOfReasoningJson = CursorUtil.getColumnIndexOrThrow(_cursor, "reasoningJson");
          final int _cursorIndexOfUserRole = CursorUtil.getColumnIndexOrThrow(_cursor, "userRole");
          final List<AnalysisLog> _result = new ArrayList<AnalysisLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnalysisLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpUserInput;
            if (_cursor.isNull(_cursorIndexOfUserInput)) {
              _tmpUserInput = null;
            } else {
              _tmpUserInput = _cursor.getString(_cursorIndexOfUserInput);
            }
            final String _tmpClassifiedModule;
            if (_cursor.isNull(_cursorIndexOfClassifiedModule)) {
              _tmpClassifiedModule = null;
            } else {
              _tmpClassifiedModule = _cursor.getString(_cursorIndexOfClassifiedModule);
            }
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final String _tmpConfidenceLevel;
            if (_cursor.isNull(_cursorIndexOfConfidenceLevel)) {
              _tmpConfidenceLevel = null;
            } else {
              _tmpConfidenceLevel = _cursor.getString(_cursorIndexOfConfidenceLevel);
            }
            final String _tmpResultJson;
            if (_cursor.isNull(_cursorIndexOfResultJson)) {
              _tmpResultJson = null;
            } else {
              _tmpResultJson = _cursor.getString(_cursorIndexOfResultJson);
            }
            final String _tmpReasoningJson;
            if (_cursor.isNull(_cursorIndexOfReasoningJson)) {
              _tmpReasoningJson = null;
            } else {
              _tmpReasoningJson = _cursor.getString(_cursorIndexOfReasoningJson);
            }
            final String _tmpUserRole;
            if (_cursor.isNull(_cursorIndexOfUserRole)) {
              _tmpUserRole = null;
            } else {
              _tmpUserRole = _cursor.getString(_cursorIndexOfUserRole);
            }
            _item = new AnalysisLog(_tmpId,_tmpTimestamp,_tmpUserInput,_tmpClassifiedModule,_tmpConfidence,_tmpConfidenceLevel,_tmpResultJson,_tmpReasoningJson,_tmpUserRole);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<AnalysisLog>> getLogsByModule(final String module) {
    final String _sql = "SELECT * FROM analysis_logs WHERE classifiedModule = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (module == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, module);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"analysis_logs"}, new Callable<List<AnalysisLog>>() {
      @Override
      @NonNull
      public List<AnalysisLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUserInput = CursorUtil.getColumnIndexOrThrow(_cursor, "userInput");
          final int _cursorIndexOfClassifiedModule = CursorUtil.getColumnIndexOrThrow(_cursor, "classifiedModule");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfResultJson = CursorUtil.getColumnIndexOrThrow(_cursor, "resultJson");
          final int _cursorIndexOfReasoningJson = CursorUtil.getColumnIndexOrThrow(_cursor, "reasoningJson");
          final int _cursorIndexOfUserRole = CursorUtil.getColumnIndexOrThrow(_cursor, "userRole");
          final List<AnalysisLog> _result = new ArrayList<AnalysisLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnalysisLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpUserInput;
            if (_cursor.isNull(_cursorIndexOfUserInput)) {
              _tmpUserInput = null;
            } else {
              _tmpUserInput = _cursor.getString(_cursorIndexOfUserInput);
            }
            final String _tmpClassifiedModule;
            if (_cursor.isNull(_cursorIndexOfClassifiedModule)) {
              _tmpClassifiedModule = null;
            } else {
              _tmpClassifiedModule = _cursor.getString(_cursorIndexOfClassifiedModule);
            }
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final String _tmpConfidenceLevel;
            if (_cursor.isNull(_cursorIndexOfConfidenceLevel)) {
              _tmpConfidenceLevel = null;
            } else {
              _tmpConfidenceLevel = _cursor.getString(_cursorIndexOfConfidenceLevel);
            }
            final String _tmpResultJson;
            if (_cursor.isNull(_cursorIndexOfResultJson)) {
              _tmpResultJson = null;
            } else {
              _tmpResultJson = _cursor.getString(_cursorIndexOfResultJson);
            }
            final String _tmpReasoningJson;
            if (_cursor.isNull(_cursorIndexOfReasoningJson)) {
              _tmpReasoningJson = null;
            } else {
              _tmpReasoningJson = _cursor.getString(_cursorIndexOfReasoningJson);
            }
            final String _tmpUserRole;
            if (_cursor.isNull(_cursorIndexOfUserRole)) {
              _tmpUserRole = null;
            } else {
              _tmpUserRole = _cursor.getString(_cursorIndexOfUserRole);
            }
            _item = new AnalysisLog(_tmpId,_tmpTimestamp,_tmpUserInput,_tmpClassifiedModule,_tmpConfidence,_tmpConfidenceLevel,_tmpResultJson,_tmpReasoningJson,_tmpUserRole);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<AnalysisLog>> searchLogs(final String query) {
    final String _sql = "SELECT * FROM analysis_logs WHERE userInput LIKE '%' || ? || '%' OR classifiedModule LIKE '%' || ? || '%' ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 2;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"analysis_logs"}, new Callable<List<AnalysisLog>>() {
      @Override
      @NonNull
      public List<AnalysisLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUserInput = CursorUtil.getColumnIndexOrThrow(_cursor, "userInput");
          final int _cursorIndexOfClassifiedModule = CursorUtil.getColumnIndexOrThrow(_cursor, "classifiedModule");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfResultJson = CursorUtil.getColumnIndexOrThrow(_cursor, "resultJson");
          final int _cursorIndexOfReasoningJson = CursorUtil.getColumnIndexOrThrow(_cursor, "reasoningJson");
          final int _cursorIndexOfUserRole = CursorUtil.getColumnIndexOrThrow(_cursor, "userRole");
          final List<AnalysisLog> _result = new ArrayList<AnalysisLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnalysisLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpUserInput;
            if (_cursor.isNull(_cursorIndexOfUserInput)) {
              _tmpUserInput = null;
            } else {
              _tmpUserInput = _cursor.getString(_cursorIndexOfUserInput);
            }
            final String _tmpClassifiedModule;
            if (_cursor.isNull(_cursorIndexOfClassifiedModule)) {
              _tmpClassifiedModule = null;
            } else {
              _tmpClassifiedModule = _cursor.getString(_cursorIndexOfClassifiedModule);
            }
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final String _tmpConfidenceLevel;
            if (_cursor.isNull(_cursorIndexOfConfidenceLevel)) {
              _tmpConfidenceLevel = null;
            } else {
              _tmpConfidenceLevel = _cursor.getString(_cursorIndexOfConfidenceLevel);
            }
            final String _tmpResultJson;
            if (_cursor.isNull(_cursorIndexOfResultJson)) {
              _tmpResultJson = null;
            } else {
              _tmpResultJson = _cursor.getString(_cursorIndexOfResultJson);
            }
            final String _tmpReasoningJson;
            if (_cursor.isNull(_cursorIndexOfReasoningJson)) {
              _tmpReasoningJson = null;
            } else {
              _tmpReasoningJson = _cursor.getString(_cursorIndexOfReasoningJson);
            }
            final String _tmpUserRole;
            if (_cursor.isNull(_cursorIndexOfUserRole)) {
              _tmpUserRole = null;
            } else {
              _tmpUserRole = _cursor.getString(_cursorIndexOfUserRole);
            }
            _item = new AnalysisLog(_tmpId,_tmpTimestamp,_tmpUserInput,_tmpClassifiedModule,_tmpConfidence,_tmpConfidenceLevel,_tmpResultJson,_tmpReasoningJson,_tmpUserRole);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getLogById(final long id, final Continuation<? super AnalysisLog> $completion) {
    final String _sql = "SELECT * FROM analysis_logs WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AnalysisLog>() {
      @Override
      @Nullable
      public AnalysisLog call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUserInput = CursorUtil.getColumnIndexOrThrow(_cursor, "userInput");
          final int _cursorIndexOfClassifiedModule = CursorUtil.getColumnIndexOrThrow(_cursor, "classifiedModule");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfResultJson = CursorUtil.getColumnIndexOrThrow(_cursor, "resultJson");
          final int _cursorIndexOfReasoningJson = CursorUtil.getColumnIndexOrThrow(_cursor, "reasoningJson");
          final int _cursorIndexOfUserRole = CursorUtil.getColumnIndexOrThrow(_cursor, "userRole");
          final AnalysisLog _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpUserInput;
            if (_cursor.isNull(_cursorIndexOfUserInput)) {
              _tmpUserInput = null;
            } else {
              _tmpUserInput = _cursor.getString(_cursorIndexOfUserInput);
            }
            final String _tmpClassifiedModule;
            if (_cursor.isNull(_cursorIndexOfClassifiedModule)) {
              _tmpClassifiedModule = null;
            } else {
              _tmpClassifiedModule = _cursor.getString(_cursorIndexOfClassifiedModule);
            }
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final String _tmpConfidenceLevel;
            if (_cursor.isNull(_cursorIndexOfConfidenceLevel)) {
              _tmpConfidenceLevel = null;
            } else {
              _tmpConfidenceLevel = _cursor.getString(_cursorIndexOfConfidenceLevel);
            }
            final String _tmpResultJson;
            if (_cursor.isNull(_cursorIndexOfResultJson)) {
              _tmpResultJson = null;
            } else {
              _tmpResultJson = _cursor.getString(_cursorIndexOfResultJson);
            }
            final String _tmpReasoningJson;
            if (_cursor.isNull(_cursorIndexOfReasoningJson)) {
              _tmpReasoningJson = null;
            } else {
              _tmpReasoningJson = _cursor.getString(_cursorIndexOfReasoningJson);
            }
            final String _tmpUserRole;
            if (_cursor.isNull(_cursorIndexOfUserRole)) {
              _tmpUserRole = null;
            } else {
              _tmpUserRole = _cursor.getString(_cursorIndexOfUserRole);
            }
            _result = new AnalysisLog(_tmpId,_tmpTimestamp,_tmpUserInput,_tmpClassifiedModule,_tmpConfidence,_tmpConfidenceLevel,_tmpResultJson,_tmpReasoningJson,_tmpUserRole);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLogCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM analysis_logs";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
