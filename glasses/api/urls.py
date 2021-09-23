from django.urls import path

from .views import GlassesListView, GlassesDetailView

urlpatterns = [
    path('glasses/all/', GlassesListView.as_view()),
    path('glasses/<int:pk>/', GlassesDetailView.as_view()),
]