lunajson = require 'lunajson'

local function hget_nems(id,hash_key)
    local redis = (require 'redis').connect('127.0.0.1', 6379)
    local flat_map = redis:hget(hash_key,id)
    local result = lunajson.decode(flat_map)
    return result.negId
end

local function hget_current(id,hash_key)
    local redis = (require 'redis').connect('127.0.0.1', 6379)
    local flat_map = redis:hget(hash_key,id)
    local result = lunajson.decode(flat_map)
    return result.currentAllocation
end


function get_currentallocation(txn)
  local redis = (require 'redis').connect('127.0.0.1', 6379)
  local value=txn:get_var("txn.value")
  if value == 'NEG-602' then
    result = hget_current('NEG-602','NEMS_NEG_MAPPING')
    print("The request is forwarded to ".. result)
  elseif value == 'NEG-1602' then
    result = hget_current('NEG-1602','NEMS_NEG_MAPPING')
    print("The request is forwarded to ".. result)
  end
  return result
end

core.register_fetches("get_currentallocation", get_currentallocation)

function get_backend(txn)
  local redis = (require 'redis').connect('127.0.0.1', 6379)
  local path = txn.sf:hdr('neg')
  local path_start = string.match(path, '([^/]+)')
  if path_start == 'NEG-602' then
    result = hget_nems('NEG-602','NEMS_NEG_MAPPING')
    print("The request is routed to the backend server ".. result)
  elseif path_start == 'NEG-1602' then
    result = hget_nems('NEG-1602','NEMS_NEG_MAPPING')
    print("The request is routed to the backend server ".. result)
  end
  return result
end

core.register_fetches("get_backend", get_backend)