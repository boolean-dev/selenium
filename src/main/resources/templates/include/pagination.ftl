<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" />
<#if page.totalPages gt 1>
<div class="clearfix">
	<div class="pagination pull-right">
		
		<#--第一页-->
		<#if page.pageNumber ==1>
			<li>
				<a class="firstPage btn btn-white disabled">
					<i class="fa fa-angle-double-left"></i>&nbsp;
				</a>
			</li>
		<#else>
			<li>
				<a class="firstPage btn btn-white" href="javascript: $.pageSkip(1);">
					<i class="fa fa-angle-double-left"></i>&nbsp;
				</a>
			</li>
		</#if>

		<#list page.segment as segmentPageNumber>
			<#if segmentPageNumber_index == 0 && segmentPageNumber gt 2>
			<li><a href="javascript:;" class="btn btn-white"><span class="pageBreak">...</span></a></li>
			</#if>

			<#if segmentPageNumber != page.pageNumber>
			<li><a href="javascript: $.pageSkip(${segmentPageNumber});" class="btn btn-white">${segmentPageNumber}</a></li>
			<#else>
			<li><a href="javascript:;" class="btn btn-white active"><span class="currentPage">${segmentPageNumber}</span></a></li>
			</#if>

			<#if segmentPageNumber_index + 1 == page.num  && segmentPageNumber < page.totalPages - 1>
			<li><a href="javascript:;" class="btn btn-white"><span class="pageBreak">...</span></a></li>
			</#if>
		</#list>
		
		
		<#--上一页-->
		<#if page.pageNumber != 1>
			<li>
				<a class="previousPage btn btn-white" href="javascript: $.pageSkip(${page.pageNumber}-1);">
					<i class="fa fa-angle-left"></i>&nbsp;
				</a>
			</li>
		<#else>
			<li>
				<a class="previousPage btn btn-white disabled">
					<i class="fa fa-angle-left"></i>&nbsp;
				</a>
			</li>
		</#if>
		

		
		<#--下一页-->
		<#if page.pageNumber != page.totalPages>
			<li>
				<a class="nextPage btn btn-white" href="javascript: $.pageSkip(${page.pageNumber}+1);">
					<i class="fa fa-angle-right"></i>&nbsp;
				</a>
			</li>
		<#else>
			<li>
				<a href="javascript:;" class="btn btn-white disabled">
					<span class="nextPage">
						<i class="fa fa-angle-right"></i>&nbsp;
					</span>
				</a>
			</li>
		</#if>
		
		<#--最后一页-->
		<#if page.pageNumber == page.totalPages>
			<li>
				<a href="javascript:;" class="btn btn-white disabled">
					<span class="lastPage">
						<i class="fa fa-angle-double-right"></i>&nbsp;
					</span>
				</a>
			</li>
		<#else>
			<li>
				<a class="lastPage btn btn-white" href="javascript: $.pageSkip(${page.totalPages});">
					<span class="lastPage">
						<i class="fa fa-angle-double-right"></i>&nbsp;
					</span>
				</a>
			</li>
		</#if>
		
		<span class="pageSkip">
			共${page.totalPages}页&nbsp;到第
			<input style="width:30px;" id="pageNumber" name="pageNumber" value="${page.pageNumber}" maxlength="9" onpaste="return false;"/>页
			<button type="submit" class="btn btn-sm btn-white">
				跳转
			</button>
		</span>
	</div>
</div>
</#if>