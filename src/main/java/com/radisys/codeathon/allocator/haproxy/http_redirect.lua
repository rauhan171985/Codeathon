lunajson = require 'lunajson'


local function hget_current(id,hash_key)
    local redis = (require 'redis').connect('127.0.0.1', 6379)
    local flat_map = redis:hget(hash_key,id)
    local result = lunajson.decode(flat_map)
    return result.currentAllocation
end

function get_token(txn)
   local http = require("socket.http")
   response, err = http.request("POST", "http://idp.ems602.dtblrrsys.com/auth/realms/a4/protocol/openid-connect/token")

end
core.register_fetches("get_token", get_token)


function get_currentallocation(txn)
  local redis = (require 'redis').connect('127.0.0.1', 6379)
  local value=txn.sf:hdr("neg")
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
