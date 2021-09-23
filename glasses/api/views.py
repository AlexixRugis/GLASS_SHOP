from rest_framework import generics

from .serializers import GlassesModelListSerializer, GlassesModelSerializer

from ..services import get_all_glasses

class GlassesListView(generics.ListAPIView):
    serializer_class = GlassesModelListSerializer

    filterset_fields = ('form', 'frame')
    search_fields = ('name')

    queryset = get_all_glasses()

class GlassesDetailView(generics.RetrieveAPIView):
    serializer_class = GlassesModelSerializer
    
    queryset = get_all_glasses()
