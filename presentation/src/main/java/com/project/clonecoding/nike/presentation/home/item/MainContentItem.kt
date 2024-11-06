package com.project.clonecoding.nike.presentation.home.item

/**
 * 메인 콘텐츠 데이터 클래스
 * - 이미지 리소스 ID, 서브 타이틀, 메인 타이틀, 버튼 텍스트, 버튼 클릭 리스너 포함
 */
data class MainContentItem(
    val id: Int,
    val imageRes: Int,
    val subtitleId: Int,
    val titleId: Int,
    val buttonTextId: Int,
)