from rest_framework import serializers
from glasses.models import GlassesModel, GlassesForm, GlassesFrame, ExtraOption

class GlassesFrameSerializer(serializers.ModelSerializer):
    class Meta:
        model = GlassesFrame
        fields = '__all__'

class GlassesFormSerializer(serializers.ModelSerializer):
    class Meta:
        model = GlassesForm
        fields = '__all__'

class ExtraOptionSerializer(serializers.ModelSerializer):
    class Meta:
        model = ExtraOption
        fields = '__all__'


class GlassesModelSerializer(serializers.ModelSerializer):
    options = ExtraOptionSerializer(many = True)

    form = GlassesFormSerializer()
    frame = GlassesFrameSerializer()

    class Meta:
        model = GlassesModel
        fields = '__all__'

class GlassesModelListSerializer(serializers.ModelSerializer):
    class Meta:
        model = GlassesModel
        fields = ('pk', 'name', 'avatar', 'current_cost', 'form', 'frame')