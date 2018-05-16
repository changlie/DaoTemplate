select 
<#list fields as f><#if f_index!=0>,</#if><#if (simpleName)??>${simpleName}.${f}<#else>${f}</#if></#list> 
from ${tableName} ${simpleName}