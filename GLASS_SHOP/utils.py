from rest_framework.pagination import LimitOffsetPagination

class VLimitOffsetPagination(LimitOffsetPagination):
    max_limit = 100